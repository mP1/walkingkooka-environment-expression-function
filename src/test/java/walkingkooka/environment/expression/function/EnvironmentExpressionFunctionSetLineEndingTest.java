/*
 * Copyright 2025 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.environment.expression.function;

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.ToStringTesting;
import walkingkooka.collect.list.Lists;
import walkingkooka.text.HasLineEndingTesting;
import walkingkooka.text.LineEnding;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;

public final class EnvironmentExpressionFunctionSetLineEndingTest extends EnvironmentExpressionFunctionTestCase<EnvironmentExpressionFunctionSetLineEnding<ExpressionEvaluationContext>, Void>
    implements HasLineEndingTesting,
    ToStringTesting<EnvironmentExpressionFunctionSetLineEnding<ExpressionEvaluationContext>> {

    private final static LineEnding LINE_ENDING = LineEnding.NL;

    @Override
    public void testSetParametersSame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void testApplyWithLineEnding() {
        final ExpressionEvaluationContext context = this.createContext();

        final LineEnding lineEnding = LineEnding.CR;
        this.applyAndCheck(
            EnvironmentExpressionFunctionSetLineEnding.instance(),
            Lists.of(lineEnding),
            context,
            null
        );

        this.lineEndingAndCheck(
            context,
            lineEnding
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            EnvironmentExpressionFunctionSetLineEnding.instance(),
            "setLineEnding"
        );
    }

    @Override
    public EnvironmentExpressionFunctionSetLineEnding createBiFunction() {
        return EnvironmentExpressionFunctionSetLineEnding.instance();
    }

    @Override
    public ExpressionEvaluationContext createContext() {
        return new FakeExpressionEvaluationContext() {
            @Override
            public LineEnding lineEnding() {
                return this.lineEnding;
            }

            @Override
            public void setLineEnding(final LineEnding lineEnding) {
                this.lineEnding = lineEnding;
            }

            private LineEnding lineEnding = EnvironmentExpressionFunctionSetLineEndingTest.LINE_ENDING;
        };
    }

    @Override
    public Class<EnvironmentExpressionFunctionSetLineEnding<ExpressionEvaluationContext>> type() {
        return Cast.to(EnvironmentExpressionFunctionSetLineEnding.class);
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }
}
