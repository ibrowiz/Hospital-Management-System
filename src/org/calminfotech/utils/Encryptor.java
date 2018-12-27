package org.calminfotech.utils;

import java.security.MessageDigest;
import java.util.List;

import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class Encryptor extends CustomHibernateDaoSupport {

	public String createActivationCode(String email) {

		String code = null;
		/*
		 * 
		 * Append system timestamp to email. That way code will be unique
		 * chances are very slim for an email to register at the very same
		 * instance.
		 */
		while (true) {
			try {
				long timestamp = System.currentTimeMillis();
				String str = email + ":" + timestamp;
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				byte[] byteData = md.digest();

				/*
				 * Generate code from MessageDigest object
				 */
				StringBuilder sf = new StringBuilder();
				for (byte b : byteData) {
					sf.append(String.format("%02x", b & 0xff));
				}
				System.out.println(sf.toString());
				code = sf.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (!this.exist(code))
				break;
		}

		return code;
	}

	public boolean exist(String activationCode) {
		List list = getHibernateTemplate().find(
				"from UserActivation where activationCode = ?", activationCode);
		if (list.size() != 0)
			return true;
		return false;
	}
}
