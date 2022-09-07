public static void addAtoB(int a, int b) {
    
    execute(a, b, new Strategy() {
            @Override
            public double doOperation(int a, int b) {
                return a + b;
            }
        });
    
}

public static void subtractBfromA(int a, int b) {
    
    execute(a, b, new Strategy() {
            @Override
            public double doOperation(int a, int b) {
                return a - b;
            }
        });
    
}

public static void multiplyAbyB(int a, int b) {
    
    execute(a, b, new Strategy() {
            @Override
            public double doOperation(int a, int b) {
                return a * b;
            }
        });
    
}

public static void divideAbyB(int a, int b) {
    
    execute(a, b, new Strategy() {
            @Override
            public double doOperation(int a, int b) {
                if (b != 0) {
                    return a / b;
                } else {
                    throw new RuntimeException("dividing  by zero");
                }
            }
        });
    
}
