package com.example.lm.xpto.systems.api.config;

import org.hibernate.dialect.H2Dialect;

public class H2DialectExtended extends H2Dialect {

    @Override
    public String toBooleanValueString(boolean bool) {
        return bool ? "TRUE" : "FALSE";
    }
}