package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

public final class ApiPathConstants {

    private ApiPathConstants() {
        throw new IllegalStateException("Utility class");
    }


    String user = "user";
    public static final String AUTH_BASE_URL = "/api/auth";
    public static final String REFRESH_TOKEN_URL = "/refresh-token";
    public static final String REGISTER_URL = "/register";
    public static final String UPDATE_PASSWORD_URL = "/user-update";
    public static final String LOGIN_URL = "/login";
    public static final String BRANDS_BASE_URL = "/api/brands";
    public static final String GET_ALL_BRANDS = "/getAll";
    public static final String GET_BRAND_BY_ID = "/{id}"; // Path variable olduğu için sabit kısmını tutuyoruz.
    public static final String ADD_BRAND = "/add";
    public static final String UPDATE_BRAND = "/update";
    public static final String DELETE_BRAND = "/{id}";
    public static final String CAR_BASE_URL = "/api/cars";
    public static final String SEARCH_BASE_URL = "/api/search";
    public static final String CAR_FILTER_URL = "/api/filter";
    public static final String CAR_SEARCH_URL = "/api/search";
    public static final String GET_ALL_CARS = "/getAll";
    public static final String GET_CAR_BY_ID = "/{id}"; // Path variable kullandığımız için formatı koruyoruz.
    public static final String ADD_CAR = "/add";
    public static final String UPDATE_CAR = "/update";
    public static final String DELETE_CAR = "/{id}";
    public static final String COLORS_BASE_URL = "/api/colors";
    public static final String GET_ALL_COLORS = "/getAll";
    public static final String GET_COLOR_BY_ID = "/{id}";
    public static final String ADD_COLOR = "/add";
    public static final String UPDATE_COLOR = "/update";
    public static final String DELETE_COLOR = "/{id}";
    public static final String CUSTOMERS_BASE_URL = "/api/customers";
    public static final String GET_ALL_CUSTOMERS = "/getAll";
    public static final String GET_CUSTOMER_BY_ID = "/{id}";
    public static final String ADD_CUSTOMER = "/add";
    public static final String UPDATE_CUSTOMER = "/update";
    public static final String DELETE_CUSTOMER = "/{id}";
    public static final String EMPLOYEES_BASE_URL = "/api/employees";
    public static final String GET_ALL_EMPLOYEES = "/getAll";
    public static final String GET_EMPLOYEE_BY_ID = "/{id}";
    public static final String ADD_EMPLOYEE = "/add";
    public static final String UPDATE_EMPLOYEE = "/update";
    public static final String DELETE_EMPLOYEE = "/{id}";
    public static final String MODELS_BASE_URL = "/api/models";
    public static final String GET_ALL_MODELS = "/getAll";
    public static final String GET_MODEL_BY_ID = "/{id}";
    public static final String ADD_MODEL = "/add";
    public static final String UPDATE_MODEL = "/update";
    public static final String DELETE_MODEL = "/{id}";
    public static final String RENTALS_BASE_URL = "/api/rentals";
    public static final String GET_ALL_RENTALS = "/getAll";
    public static final String GET_RENTAL_BY_ID = "/{id}";
    public static final String ADD_RENTAL = "/add";
    public static final String ADD_RENTAL_ADMIN = "/admin/add";
    public static final String UPDATE_RENTAL = "/update"; // Not: "/update" yoluna path variable eklenecekse bu sabite de eklenmeli.
    public static final String DELETE_RENTAL = "/{id}";

    public static final String ROLES_BASE_URL = "/api/roles";
    public static final String GET_ALL_ROLES = "/getAll";
    public static final String GET_ROLE_BY_ID = "/{id}";
    public static final String ADD_ROLE = "/add";
    public static final String UPDATE_ROLE = "/update";
    public static final String DELETE_ROLE = "/{id}";
    public static final String USERS_BASE_URL = "/api/users";
    public static final String GET_ALL_USERS = "/getAll";
    public static final String GET_USER_BY_ID = "/{id}";
    public static final String ADD_USER = "/add";
    public static final String UPDATE_USER = "/update";
    public static final String DELETE_USER = "/{id}";
    public static final String GET_USER_BY_EMAIL = "/getByEmail";
    public static final String INVOICE_BASE_URL = "/api/invoices";
    public static final String GET_ALL_INVOICES = "/getAll";
    public static final String GET_INVOICE_BY_ID = "/{id}";
    public static final String ADD_INVOICE = "/add";
    public static final String UPDATE_INVOICE = "/update";
    public static final String DELETE_INVOICE = "/{id}";
    public static final String ADMIN_BASE_URL = "/admin";
    public static final String UPDATE_ADMIN = "/update";


}
