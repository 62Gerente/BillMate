package com.billmate

import java.security.MessageDigest
import org.apache.commons.codec.binary.Base64

class PasswordCodec {
    static encode = { String s ->
        MessageDigest md = MessageDigest.getInstance('SHA')
        md.update s.getBytes('UTF-8')
        Base64.encodeBase64 md.digest()
    }
}