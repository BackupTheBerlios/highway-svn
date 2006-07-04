/*
 * Created on 25 oct. 2004
 */
package org.highway.sample.application.gererfacture;

import org.highway.bean.Decimal;
import org.highway.exception.FunctionalException;
import org.highway.exception.UserMessage;
import org.highway.helper.Wrapper;

/**
 * @author attias
 */
public class MauvaisFournisseurException extends FunctionalException
{
	private UserMessage userMessage;
	
	public MauvaisFournisseurException(Decimal montantBloque, String nom)
	{
		this.userMessage = new UserMessage(
			"MSG0000324",
			Wrapper.toList(nom, montantBloque),
			false);
	}
	
	public UserMessage getUserMessage()
	{
		return userMessage;
	}
}
