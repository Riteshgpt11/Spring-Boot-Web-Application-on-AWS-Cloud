package com.csye6225.demo.Service;

public class FileArchiveServiceException extends RuntimeException {
    private static final long serialVersionUID = 2468434988680850339L;

    public FileArchiveServiceException(String s, Throwable ex) {
        super(s, ex);
    }
}
