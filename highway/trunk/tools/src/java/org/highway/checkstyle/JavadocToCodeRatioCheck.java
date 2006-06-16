package org.highway.checkstyle;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.Comment;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class JavadocToCodeRatioCheck extends Check
{

	public final static char STATS_DELIMITER = '/';

	private final static String COMMENT_BLOCK_START = "/*";

	private final static String COMMENT_BLOCK_END = "*/";

	private final static String COMMENT_INLINE = "//";

	private int nbCommentingLines;

	private int nbTotalLines;

	public int[] getDefaultTokens()
	{
		// not interested in any token, we parse the file ourselves
		return new int[] {};
	}

	public void beginTree(DetailAST aRootAST)
	{
		// retrieve all comments
		Map blockComments = this.getFileContents().getCComments();
		Map inlineComments = this.getFileContents().getCppComments();

		// the number of commenting lines
		this.nbCommentingLines = 0;
		this.nbTotalLines = 0;

		// calculate the number of lines in each category
		Iterator blockCommentsIterator = blockComments.values().iterator();
		while (blockCommentsIterator.hasNext())
		{
			List commentsList = (List) blockCommentsIterator.next();
			Iterator iter = commentsList.iterator();
			while (iter.hasNext())
			{
				String[] commentsText = ((Comment) iter.next()).getText();
				// TODO prefer (comment.end - comment.start + 1) ?
				for (int i = 0; i < commentsText.length; i++)
				{
					this.nbCommentingLines++;
				}
			}
		}

		Iterator inlineCommentsIterator = inlineComments.values().iterator();
		while (inlineCommentsIterator.hasNext())
		{
			String[] commentsText = ((Comment) inlineCommentsIterator.next())
				.getText();
			// TODO prefer (comment.end - comment.start + 1) ?
			for (int i = 0; i < commentsText.length; i++)
			{
				this.nbCommentingLines++;
			}
		}

		this.nbTotalLines = this.getFileContents().getLines().length;
	}

	public void finishTree(DetailAST aRootAST)
	{
		// report the number of commenting/total lines

		// the following line would have been nice, but ints are pretty printed
		// according to Locale... so let's be basic here
		// this.log(aRootAST, "{0}" + STATS_DELIMITER + "{1}", new Integer(
		// this.nbCommentingLines), new Integer(this.nbTotalLines));

		this.log(aRootAST, "" + this.nbCommentingLines + STATS_DELIMITER
			+ this.nbTotalLines);
	}

}
