package com.mandi.sesitive;

import com.mandi.sesitive.annotation.*;

public class Child {
    @ChineseNameSensitive
    private String name = "李富贵";

    @IdCardNumberSensitive
    private String idCardNumber = "321181199301096000";

    @UsccSensitive
    private String unifiedSocialCreditCode = "91310106575855456U";

    @CharSequenceSensitive
    private String string = "123456";

    @EmailSensitive
    private String email = "123456@qq.com";

    @PasswordSensitive
    private String password = "123456";

    private @PasswordSensitive
    String[] passwords = {"123456", "1234567", "12345678"};


}
