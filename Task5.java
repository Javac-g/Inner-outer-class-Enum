enum ClientType {
    NEW(1),

    SILVER(12) {
        @Override
        public double discount() {
            return (100 - getMonths() * 0.25) / 100;
        }
    },

    GOLD(30) {
        @Override
        public double discount() {
            return (100 - getMonths() * 0.3) / 100;
        }
    },

    PLATINUM(60) {
        @Override
        public double discount() {
            return (100 - getMonths() * 0.35) / 100;
        }
    };

    private int months;

    private ClientType(int months) {
        this.months = months;
    }

    public double discount() {
        return 1.0;
    }

    public int getMonths() {
        return months;
    }
}
