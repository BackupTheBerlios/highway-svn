package net.sf.jelly.apt.strategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.sf.jelly.apt.TemplateBlock;
import net.sf.jelly.apt.TemplateException;
import net.sf.jelly.apt.TemplateModel;

import com.sun.mirror.declaration.AnnotationMirror;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.Declaration;

public abstract class AnnotationFilterableDeclarationLoopStrategy <D extends Declaration, B extends TemplateBlock> extends DeclarationLoopStrategy<D, B> {

	  private String annotation;
	  private String annotationVar;

	  //Inherited.
	  @Override
	  protected void setupModelForLoop(TemplateModel model, D declaration, int index) throws TemplateException {
	    super.setupModelForLoop(model, declaration, index);

	    if (annotationVar != null) {
	      Collection<AnnotationMirror> annotations = getCurrentDeclaration().getAnnotationMirrors();
	      for (AnnotationMirror mirror : annotations) {
	        AnnotationTypeDeclaration annotation = mirror.getAnnotationType().getDeclaration();
	        if (annotation != null) {
	          if (annotation.getQualifiedName().equals(this.annotation)) {
	            model.setVariable(annotationVar, mirror);
	          }
	        }
	      }
	    }
	  }

	  /**
	   * Get the list of all declarations to consider.
	   *
	   * @return The list of all declarations to consider.
	   */
	  public abstract Collection<D> getAllDeclarationsToConsiderForAnnotationFiltering() throws MissingParameterException;

	  /**
	   * The filtered list of declarations.
	   *
	   * @return The filtered list of declarations.
	   */
	  public Collection<D> getDeclarations() throws MissingParameterException {
	    if (annotation == null) {
	      return getAllDeclarationsToConsiderForAnnotationFiltering();
	    }

	    //I can't guarantee that the collection of unfiltered declarations is modifiable,
	    //so I'm putting them in my own ArrayList.
	    ArrayList<D> declarations = new ArrayList<D>(getAllDeclarationsToConsiderForAnnotationFiltering());
	    Iterator<D> it = declarations.iterator();
	    while (it.hasNext()) {
	      D declaration = it.next();
	      if (!hasAnnotation(declaration, annotation)) {
	        it.remove();
	      }
	    }
	    return declarations;
	  }

	  /**
	   * Whether the given declaration is annotated with an annotation that has the given
	   * (fully-qualified) annotation name.
	   *
	   * @param declaration The declaration.
	   * @param annotationName The annotation name.
	   * @return Whether the declaration has the annotation.
	   */
//	  protected boolean hasAnnotation(D declaration, String annotationName) {
//	    for (AnnotationMirror mirror : declaration.getAnnotationMirrors()) {
//	      AnnotationTypeDeclaration annotation = mirror.getAnnotationType().getDeclaration();
//	      if (annotation != null) {
//	        return annotation.getQualifiedName().equals(annotationName);
//	      }
//	    }
//	    return false;
//	  }
		protected boolean hasAnnotation(D declaration, String annotationName) {
		System.out.println("nico touch");
		for (AnnotationMirror mirror : declaration.getAnnotationMirrors()) {
		  AnnotationTypeDeclaration annotation = mirror.getAnnotationType().getDeclaration();
		  if (annotation != null) {
		    return annotation.getQualifiedName().equals(annotationName);
		  }
		}
		return false;
		}
	  /**
	   * Optional annotation by which to filter the classes.
	   *
	   * @return Optional annotation by which to filter the classes.
	   */
	  public String getAnnotation() {
	    return annotation;
	  }

	  /**
	   * Optional annotation by which to filter the classes.
	   *
	   * @param annotation Optional annotation by which to filter the classes.
	   */
	  public void setAnnotation(String annotation) {
	    this.annotation = annotation;
	  }

	  /**
	   * The context variable in which to store the annotation, if {@link #setAnnotation(String) specified}.
	   *
	   * @return The context variable in which to store the annotation, if {@link #setAnnotation(String) specified}.
	   */
	  public String getAnnotationVar() {
	    return annotationVar;
	  }

	  /**
	   * The context variable in which to store the annotation, if {@link #setAnnotation(String) specified}.
	   *
	   * @param annotationVar The context variable in which to store the annotation, if {@link #setAnnotation(String) specified}.
	   */
	  public void setAnnotationVar(String annotationVar) {
	    this.annotationVar = annotationVar;
	  }

}
