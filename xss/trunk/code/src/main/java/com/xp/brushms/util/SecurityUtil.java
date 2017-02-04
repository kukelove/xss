package com.xp.brushms.util;

import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.*;


public class SecurityUtil {
	static {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	public static String doMd5(String data) {
		MessageDigest md=null;  
        try {  
            md=MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        
        md.update(data.getBytes());  
        byte[] bs=md.digest();   
        StringBuffer sb=new StringBuffer();  
        for(int i=0;i<bs.length;i++){    
            int v=bs[i]&0xff;  
            if(v<16){  
                sb.append(0);  
            }  
            sb.append(Integer.toHexString(v));  
        } 
        return sb.toString();
	}
	@SuppressWarnings("deprecation")
	public static String sign(String message) {
		PEMReader reader = null;
		try {			
			reader = new PEMReader(new InputStreamReader(SecurityUtil.class.getResourceAsStream("private.pem")));
	        KeyPair pair = (KeyPair)reader.readObject();
	        KeyFactory fac = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = pair.getPrivate();
			Signature signature = Signature.getInstance("MD5WithRSA");  
            signature.initSign(privateKey);  
            signature.update(message.getBytes());
            byte[] signed = signature.sign();
            return new String(Base64.encode(signed));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	private SecurityUtil() {}
}
