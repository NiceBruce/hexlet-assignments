package exercise;

// BEGIN
class NegativeRadiusException extends Exception {

    private String message;

    public NegativeRadiusException(String message) {
//        this.errorCode = errorCode;
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

}
// END
