package com.example.customerdocumentsystem.exception;

public class DocumentServiceOperationException {

    public static class FolderNotFoundException extends BaseException {

        public FolderNotFoundException(String message) {
            super(message);
        }
    }

}
