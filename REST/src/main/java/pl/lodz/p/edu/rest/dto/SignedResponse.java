package pl.lodz.p.edu.rest.dto;

public class SignedResponse<T> {
    private T data;
    private String signature;

    public SignedResponse(T data, String signature) {
        this.data = data;
        this.signature = signature;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
