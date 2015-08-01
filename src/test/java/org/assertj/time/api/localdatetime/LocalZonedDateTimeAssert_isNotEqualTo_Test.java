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
package org.assertj.time.api.localdatetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.time.api.Assertions.assertThat;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Only test String based assertion (tests with {@link LocalDateTime} are already defined in assertj-core)
 * 
 * @author Joel Costigliola
 */
@RunWith(Theories.class)
public class LocalZonedDateTimeAssert_isNotEqualTo_Test extends LocalDateTimeAssertBaseTest {

  @Theory
  public void test_isNotEqualTo_assertion(LocalDateTime referenceDate) {
    // WHEN
    assertThat(referenceDate).isNotEqualTo(referenceDate.plusDays(1).toString());
    // THEN
    verify_that_isNotEqualTo_assertion_fails_and_throws_AssertionError(referenceDate);
  }

  @Test
  public void test_isNotEqualTo_assertion_error_message() {
    try {
      assertThat(new LocalDateTime(2000, 1, 5, 3, 0, 5))
          .isNotEqualTo(new LocalDateTime(2000, 1, 5, 3, 0, 5).toString());
    } catch (AssertionError e) {
      assertThat(e).hasMessage("\nExpecting:\n <2000-01-05T03:00:05.000>\nnot to be equal to:\n <2000-01-05T03:00:05.000>\n");
      return;
    }
    fail("Should have thrown AssertionError");
  }

  @Test
  public void should_fail_if_dateTime_as_string_parameter_is_null() {
    expectException(IllegalArgumentException.class,
        "The String representing the LocalDateTime to compare actual with should not be null");
    assertThat(new LocalDateTime()).isNotEqualTo((String) null);
  }

  private static void verify_that_isNotEqualTo_assertion_fails_and_throws_AssertionError(LocalDateTime reference) {
    try {
      assertThat(reference).isNotEqualTo(reference.toString());
    } catch (AssertionError e) {
      // AssertionError was expected
      return;
    }
    fail("Should have thrown AssertionError");
  }

}
