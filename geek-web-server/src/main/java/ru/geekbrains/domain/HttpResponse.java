package ru.geekbrains.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private int statusCode;

    private String statusCodeName;

    private Map<String, String> headers = new HashMap<>();

    private String body;

    private HttpResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String getStatusCodeName() {
        return statusCodeName;
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {

        private final HttpResponse response = new HttpResponse();

        public Builder withStatusCode(int statusCode) {
            this.response.statusCode = statusCode;
            return this;
        }

        public Builder withStatusCodeName(String statusCodeName) {
            this.response.statusCodeName = statusCodeName;
            return this;
        }

        public Builder withHeader(String key, String value) {
            this.response.getHeaders().put(key, value);
            return this;
        }

        public Builder withBody(String body) {
            this.response.body = body;
            return this;
        }

        public HttpResponse build() {
            if (this.response.statusCodeName == null) {
                throw new IllegalStateException("Status code not defined");
            }
            return response;
        }
    }
}
