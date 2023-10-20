package edu.hw2.Task1;

import java.util.Objects;

public sealed interface Expression {
    double evaluate();

    record Constant(double number) implements Expression {
        @Override
        public double evaluate() {
            return number;
        }
    }

    record Negate(Expression expression) implements Expression {
        @Override
        public double evaluate() {
            Objects.requireNonNull(expression);

            return -expression.evaluate();
        }
    }

    record Exponent(Expression expression, double power) implements Expression {
        @Override
        public double evaluate() {
            Objects.requireNonNull(expression);

            return Math.pow(expression.evaluate(), power);
        }
    }

    record Addition(Expression expression1, Expression expression2) implements Expression {
        @Override
        public double evaluate() {
            Objects.requireNonNull(expression1);
            Objects.requireNonNull(expression2);

            return expression1.evaluate() + expression2.evaluate();
        }
    }

    record Multiplication(Expression expression1, Expression expression2) implements Expression {
        @Override
        public double evaluate() {
            Objects.requireNonNull(expression1);
            Objects.requireNonNull(expression2);

            return expression1.evaluate() * expression2.evaluate();
        }
    }
}
