package mts.domain.register;

import mts.domain.Person;

public class AnswerCityRegisterItem {

    public enum CityState {
        YES, NO, ERROR;
    }

    public static class  CityError {
        private String code;
        private String text;

        public CityError(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public String getText() {
            return text;
        }
    }

    private CityState state;
    private Person person;
    private CityError error;

    public AnswerCityRegisterItem(CityState state, Person person) {
        this.state = state;
        this.person = person;
    }

    public AnswerCityRegisterItem(CityState state, Person person, CityError error) {
        this.state = state;
        this.person = person;
        this.error = error;
    }

    public CityState getState() {
        return state;
    }

    public Person getPerson() {
        return person;
    }

    public CityError getError() {
        return error;
    }
}
