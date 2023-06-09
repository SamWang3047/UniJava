package COMP90041.Week9.Cake_Inc;

public enum Machine {
    B('b'), W('w'), S('s'), C('c');

    char code;
    Machine(char code) {
        this.code = code;
    }

    static Machine fromCode(char code){
        switch (code){
            case 'b':
                return B;
            case 'w':
                return W;
            case 's':
                return S;
            case 'c':
                return C;
            default:
                return null;
        }
    }
}
