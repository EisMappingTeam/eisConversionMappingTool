/* XXXCopyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
/**
 *
 */
package com.exigen.ipb.base.datatypes;

import com.exigen.ipb.base.annotations.CanExtend;
import com.exigen.ipb.base.annotations.CanInvoke;

/**
 * @author VaidasN
 * @author albert
 * @since 1.0
 */
@CanInvoke
@CanExtend
public abstract class DataTypeFieldLengthConstants {

    /**
     * defines length for lookup driven fields
     */
    public static final int CODE_TYPE_FIELD_LENGHT = 20;

    /**
     * defines length for ID strings (policy number, customer no, document no etc)
     */
    public static final int ID_TYPE_FIELD_LENGHT = 20;

    /**
     * defines length for name fields (person usually)
     */
    public static final int NAME_TYPE_FIELD_LENGHT = 50;

    /**
     * For objects which names are exceeding 50 characters
     */
    public static final int LONG_NAME_TYPE_FIELD_LENGHT = 100;

    /**
     * defines length for description fields
     */
    public static final int DESC_TYPE_FIELD_LENGHT = 255;

    /**
     * defines length for long description fields
     */
    public static final int LONG_DESC_TYPE_FIELD_LENGHT = 1024;

    /**
     * defines default length
     */
    public static final int OTHER_TYPE_FIELD_LENGHT = 50;

    /**
     * UUID field length
     */
    public static final int UUID_FIELD_LENGHT = 36;

    /**
     * Email field length
     */
    public static final int EMAIL_FIELD_LENGHT = 320;

    /**
     * Address line length
     */
    public static final int ADDRESS_LINE_FIELD_LENGHT = 40;

    /**
     * City line length
     */
    public static final int CITY_LINE_FIELD_LENGHT = 30;

    /**
     * Postal code line length
     */
    public static final int POSTAL_CODE_TYPE_FIELD_LENGHT = 10;

    /**
     * Report status line length
     */
    public static final int REPORT_STATUS_FIELD_LENGTH = 255;

    /**
     * Report status line length
     */
    public static final int RELATIONSHIP_TO_CARRIER_FIELD_LENGTH = 100;

    /**
     * defines length for error log description fields
     */
    public static final int ERROR_LOG_LENGTH = 2000;

}
