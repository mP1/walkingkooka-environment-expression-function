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
import walkingkooka.collect.list.Lists;
import walkingkooka.environment.EnvironmentValueName;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;

import java.util.Optional;

public final class EnvironmentExpressionFunctionSetEnvTest extends EnvironmentExpressionFunctionTestCase<EnvironmentExpressionFunctionSetEnv<ExpressionEvaluationContext>, Object> {

    private final static EnvironmentValueName<?> VAR = EnvironmentValueName.with(
        "var123",
        String.class
    );

    private final static Object VALUE = "value123";

    @Test
    public void testApply() {
        this.applyAndCheck(
            Lists.of(
                VAR,
                VALUE
            ),
            VAR.value() + VAR.value()
        );

        this.checkEquals(
            VALUE,
            this.setValue
        );
    }

    @Override
    public EnvironmentExpressionFunctionSetEnv<ExpressionEvaluationContext> createBiFunction() {
        return EnvironmentExpressionFunctionSetEnv.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 0;
    }

    @Override
    public ExpressionEvaluationContext createContext() {
        return new FakeExpressionEvaluationContext() {

            @Override
            public <T> Optional<T> environmentValue(final EnvironmentValueName<T> n) {
                return Optional.of(
                    Cast.to(
                        n.value() + n.value()
                    )
                );
            }

            @Override
            public <T> ExpressionEvaluationContext setEnvironmentValue(final EnvironmentValueName<T> name,
                                                                       final T value) {
                checkEquals(VAR, name);
                checkEquals(VALUE, value);

                setValue = value;
                return this;
            }
        };
    }

    private Object setValue;

    // class............................................................................................................

    @Override
    public Class<EnvironmentExpressionFunctionSetEnv<ExpressionEvaluationContext>> type() {
        return Cast.to(EnvironmentExpressionFunctionSetEnv.class);
    }
}
