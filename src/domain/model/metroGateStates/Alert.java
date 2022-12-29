package domain.model.metroGateStates;

public class Alert extends RuntimeException{
        private static final long serialVersionUID = 1L;

        public Alert() {
            super();
        }

        public Alert(String message, Throwable exception) {
            super(message, exception);
        }

        public Alert(String message) {
            super(message);
        }

        public Alert(Throwable exception) {
            super(exception);
        }
}
