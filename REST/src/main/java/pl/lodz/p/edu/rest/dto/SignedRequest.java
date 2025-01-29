package pl.lodz.p.edu.rest.dto;

public class SignedRequest<T> {
    private T data;
    private String signature;

    public SignedRequest() {}

    public SignedRequest(T data, String signature) {
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
