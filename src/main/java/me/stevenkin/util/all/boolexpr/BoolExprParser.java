package me.stevenkin.util.all.boolexpr;

import java.util.Scanner;

public class BoolExprParser {
    private String expr;
    private int p;

    public BoolExprParser(String expr, int p) {
        this.expr = expr;
        this.p = p;
    }

    public boolean parse() {
        char c = expr.charAt(p);
        if (c == 't') {
            p++;
            return true;
        }
        if (c == 'f') {
            p++;
            return false;
        }
        switch (c) {
            case '!': {
                p += 2;
                boolean b = parse();
                if (expr.charAt(p) != ')')
                    throw new RuntimeException();
                p++;
                return !b;
            }
            case '&': {
                p += 2;
                boolean r = true;
                while (true) {
                    if (!parse())
                        r = false;
                    if (expr.charAt(p) == ',') {
                        p++;
                        continue;
                    }
                    if (expr.charAt(p) == ')') {
                        p++;
                        return r;
                    }
                    throw new RuntimeException();
                }
            }
            case '|': {
                p += 2;
                boolean r = false;
                while (true) {
                    if (parse())
                        r = true;
                    if (expr.charAt(p) == ',') {
                        p++;
                        continue;
                    }
                    if (expr.charAt(p) == ')') {
                        p++;
                        return r;
                    }
                    throw new RuntimeException();
                }
            }
            default:
                throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        /*Scanner in = new Scanner(System.in);
        String expr = in.nextLine();*/
        String expr = "!(&(!(t),&(f),|(f)))";
        System.out.println(new BoolExprParser(expr, 0).parse());
    }

}
