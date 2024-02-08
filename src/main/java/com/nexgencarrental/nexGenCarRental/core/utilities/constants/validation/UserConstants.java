package com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation;

public class UserConstants {
    public static final String USER_ID_POSITIVE = ("User Id cannot be less than 0.");
    public static final String USER_ID_NULL = ("User ID cannot be null");
    public static final String NAME_SIZE = ("The name cannot be less than 2 letters.");
    public static final String NAME_BLANK = ("Name cannot be blank");
    public static final String NAME_PATTERN = ("Enter upper case followed by lower case.'Name'");
    public static final String SURNAME_SIZE = ("The name cannot be less than 2 letters.");
    public static final String SURNAME_BLANK = ("Surname cannot be blank");
    public static final String SURNAME_PATTERN = ("Enter upper case followed by lower case.'Surname'");
    public static final String EMAIL_BLANK = ("E-mail cannot be blank");
    public static final String EMAIL_FORMAT = ("Invalid e-mail format");
    public static final String EMAIL_PATTERN = ("Invalid e-mail format. It must contain @ and at least one dot.");
    public static final String NATIONALITY_BLANK = ("Nationality ID cannot be blank");
    public static final String NATIONALITY_PATTERN = ("Invalid National ID format. It must be an 11-digit number.");
    public static final String GSM_BLANK = ("GSM cannot be blank");
    public static final String GSM_PATTERN = ("Invalid GSM format. It must be a 10-digit number.");
}
