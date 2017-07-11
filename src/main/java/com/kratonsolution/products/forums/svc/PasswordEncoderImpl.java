/**
 * 
 */
package com.kratonsolution.products.forums.svc;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@Service
public class PasswordEncoderImpl implements PasswordEncoder
{
	private StrongPasswordEncryptor encrypt = new StrongPasswordEncryptor();
	
	@Override
	public String encode(CharSequence rawPassword)
	{
		return encrypt.encryptPassword(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)
	{
		return encrypt.checkPassword(rawPassword.toString(),encodedPassword);
	}
}
