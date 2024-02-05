package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

public final class ErrorConstants {
    private ErrorConstants() {

    }

    public static final String USER_ALREADY_EXISTS = "user.already.exists";
    public static final String ROLE_NOT_FOUND = "role.not.found";
    public static final String INVALID_CREDENTIALS = "invalid.credentials";
    public static final String USER_NOT_FOUND = "user.not.found";
    public static final String REGISTRATION_ERROR = "registration.error";
    public static final String LOGIN_ERROR = "login.error";
    public static final String AUTH_RESPONSE_ERROR = "auth.response.error";
    public static final String ENTITY_NOT_FOUND = "Entity not found.";
    public static final String ENTITY_NOT_FOUND_WITH_ID = "Entity with ID %s not found.";
    public static final String NO_ENTITIES_FOUND = "No entities found in the system.";
    public static final String FIND_REFRESH_TOKEN_ERROR = "Error during finding refresh token by token";
    public static final String USERS_NOT_FOUND = "User not found with id: %s";
    public static final String CREATE_REFRESH_TOKEN_ERROR = "Error during creating refresh token";
    public static final String DELETE_REFRESH_TOKEN_ERROR = "Error during deleting refresh tokens by user id";
    public static final String REFRESH_TOKEN_EXPIRED = "Refresh token was expired.";
    public static final String INVALID_REFRESH_TOKEN = "Invalid Refresh Token";
    public static final String INVALID_REFRESH_TOKEN_REQUEST = "Invalid Refresh Token Request";
    public static final String ERROR_ADDING_USER = "Error during adding user";
    public static final String UNEXPECTED_ERROR_ADDING_USER = "Unexpected error during adding user";
    public static final String ERROR_GETTING_USER_BY_EMAIL = "Error during getting user by email";
    public static final String UNEXPECTED_ERROR_GETTING_USER_BY_EMAIL = "Unexpected error during getting user by email";
    public static final String ERROR_FINDING_USER_BY_EMAIL = "Error during finding user by email";
    public static final String UNEXPECTED_ERROR_FINDING_USER_BY_EMAIL = "Unexpected error during finding user by email";
    public static final String ERROR_CHECKING_USER_EXISTS_BY_EMAIL = "Error during checking if user exists by email";
    public static final String UNEXPECTED_ERROR_CHECKING_USER_EXISTS_BY_EMAIL = "Unexpected error during checking if user exists by email";
    public static final String ERROR_FINDING_ROLE_BY_ID = "Error during finding role by id";
    public static final String UNEXPECTED_ERROR_FINDING_ROLE_BY_ID = "Unexpected error during finding role by id";
    public static final String ERROR_FINDING_USER_BY_ID = "Error during finding user by id";
    public static final String UNEXPECTED_ERROR_FINDING_USER_BY_ID = "Unexpected error during finding user by id";
    public static final String NO_USER_FOUND = "No user found!";
    public static final String ERROR_LOADING_USER_BY_USERNAME = "Error during loading user by username";
    public static final String UNEXPECTED_ERROR_LOADING_USER_BY_USERNAME = "Unexpected error during loading user by username";
    public static final String BRAND_NAME_SIZE = "Enter a brand consisting of at least 2 letters";
    public static final String BRAND_NAME_PATTERN = "Enter the first letter Upper and the following letters Small without spaces. (Ex: 'Ford')";
    public static final String ID_POSITIVE = "Id field cannot be less than 0.";
    public static final String KILOMETER_MIN = "Enter zero or a value greater than zero";
    public static final String YEAR_MIN = "Between 2005 and 2024";
    public static final String YEAR_MAX = "No older than 2024";
    public static final String DAILY_PRICE_MIN = "Daily price cannot be less than zero";
    public static final String PLATE_PATTERN = "'34 ABC 456' enter according to this format";
    public static final String MODEL_ID_POSITIVE = "Model Id cannot be less than 0.";
    public static final String COLOR_ID_POSITIVE = "Color Id cannot be less than 0.";
    public static final String COLOR_NAME_SIZE = "Enter a color consisting of at least 2 letters";
    public static final String COLOR_NAME_PATTERN = "Enter the first letter UPPER and the following letters SMALL without spaces.(Ex:'White')";
    public static final String UPDATE_COLOR_ID_POSITIVE = "Id field cannot be less than 0.";
    public static final String MODEL_NAME_SIZE = "Enter a model name consisting of at least 2 letters";
    public static final String MODEL_NAME_PATTERN = "Enter the first letter in CAPITAL and the following letters in SMALL. (Ex: 'Focus')";
    public static final String ADD_MODEL_BRAND_ID_POSITIVE = "Brand Id must be a positive value.";
    public static final String UPDATE_MODEL_ID_POSITIVE = "Id cannot be less than 0";
    public static final String UPDATE_RENTAL_ID_POSITIVE = "Id cannot be less than 0";
    public static final String ADD_ROLE_NAME_SIZE = "Enter a role consisting of at least 2 letters";
    public static final String UPDATE_ROLE_ID_POSITIVE = "Id field cannot be less than 0.";

    public static final String BRAND_NAME_ALREADY_EXISTS = "The Brand name is already exists!";
    public static final String CAR_PLATE_ALREADY_EXISTS = "This license plate is already in the system. Please enter a different license plate.";

    public static final String COLOR_NAME_ALREADY_EXISTS = "The Color name is already exists!";
    public static final String CUSTOMER_NATIONALITY_ID_ALREADY_EXISTS = "The Customer Nationality Id is already exists!";
    public static final String MODEL_NAME_ALREADY_EXISTS = "The Model name is already exists!";
    public static final String START_DATE_BEFORE_TODAY = "Start date cannot be earlier than today, which is ";
    public static final String END_DATE_BEFORE_START_DATE = "The entered date must be after ";
    public static final String RENTAL_MIN_MAX_DAYS = "A Car can be rented for a minimum of 1 day and a maximum of 25 days.";
    public static final String UPDATED_START_DATE_BEFORE_TODAY = "Start date for the updated rental cannot be earlier than today, which is ";
    public static final String UPDATED_END_DATE_BEFORE_START_DATE = "The entered date for the updated rental must be after ";
    public static final String UPDATED_RENTAL_MIN_MAX_DAYS = "The updated rental must be for a minimum of 1 day and a maximum of 25 days.";

    public static final String USER_NAME_ALREADY_EXISTS = "The User name is already exists!";







}
