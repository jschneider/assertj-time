/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2013 the original author or authors.
 */
package org.assertj.time.api.datetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.time.api.Assertions.assertThat;
import static org.joda.time.DateTimeZone.UTC;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Only test String based assertion (tests with {@link DateTime} are already defined in assertj-core)
 * 
 * @author Joel Costigliola
 */
@RunWith(Theories.class)
public class DateTimeAssert_isNotIn_errors_Test extends DateTimeAssertBaseTest {

  @Theory
  public void test_isNotIn_assertion(DateTime referenceDate) {
    // WHEN
    assertThat(referenceDate).isNotIn(referenceDate.plus(1).toString(), referenceDate.plus(2).toString());
    // THEN
    verify_that_isNotIn_assertion_fails_and_throws_AssertionError(referenceDate);
  }

  @Test
  public void test_isNotIn_assertion_error_message() {
    try {
      assertThat(new DateTime(2000, 1, 5, 3, 0, 5, UTC)).isNotIn(new DateTime(2000, 1, 5, 3, 0, 5, UTC).toString(),
          new DateTime(2012, 1, 1, 3, 3, 3, UTC).toString());
    } catch (AssertionError e) {
      assertThat(e).hasMessage(
          "\nExpecting:\n <2000-01-05T03:00:05.000Z>\nnot to be in:\n"
              + " <[2000-01-05T03:00:05.000Z, 2012-01-01T03:03:03.000Z]>\n");
      return;
    }
    fail("Should have thrown AssertionError");
  }

  @Test
  public void should_fail_if_dateTimes_as_string_array_parameter_is_null() {
    expectException(IllegalArgumentException.class, "The given DateTime array should not be null");
    assertThat(new DateTime()).isNotIn((String[]) null);
  }

  @Test
  public void should_fail_if_dateTimes_as_string_array_parameter_is_empty() {
    expectException(IllegalArgumentException.class, "The given DateTime array should not be empty");
    assertThat(new DateTime()).isNotIn(new String[0]);
  }

  private static void verify_that_isNotIn_assertion_fails_and_throws_AssertionError(DateTime reference) {
    try {
      assertThat(reference).isNotIn(reference.toString(), reference.plus(1).toString());
    } catch (AssertionError e) {
      // AssertionError was expected
      return;
    }
    fail("Should have thrown AssertionError");
  }

}
