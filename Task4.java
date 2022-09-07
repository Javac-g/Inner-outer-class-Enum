
enum LineType {
    SOLID, DOTTED, DASHED, DOUBLE;
}
public static String drawLine(LineType lineType) {

    return "The line is " + lineType.name().toLowerCase() + " type";

}
