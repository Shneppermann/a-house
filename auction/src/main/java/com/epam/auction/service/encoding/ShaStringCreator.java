package com.epam.auction.service.encoding;

import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Creates SHA-1 string
 */

public class ShaStringCreator {
    private static final Logger LOGGER = LogManager.getLogger(ShaStringCreator.class);
    private static final String SHA = "SHA-1";
    private static final String UTF = "UTF-8";
    private static final String FORMAT = "%02x";


    /**
     * Convert simple string into the SHA-1 string
     *
     * @param password simple string
     * @return SHA-1 string
     * @throws LogicException when any exceptions occurred
     */
    public static String sha(String password) throws LogicException {
        String sha1;
        try {
            MessageDigest crypt = MessageDigest.getInstance(SHA);
            crypt.reset();
            crypt.update(password.getBytes(UTF));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format(FORMAT, b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
