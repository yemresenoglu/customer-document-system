package com.example.customerdocumentsystem.exception;

public class CustomerServiceOperationException {

    public static class CustomerExistException extends BaseException {

        public CustomerExistException(String message) {
            super(message);
        }
    }

    public static class CustomerNotFoundException extends BaseException {

        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    public static class CustomerNotValidException extends BaseException {
        public CustomerNotValidException(String message) {
            super(message);
        }
    }

    public static class CustomerAlreadyDeletedException extends BaseException {

        public CustomerAlreadyDeletedException(String message) {
            super(message);
        }
    }
}
