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
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.time.api.ZonedDateTimeAssert.NULL_DATE_TIME_PARAMETER_MESSAGE;
import static org.assertj.time.api.Assertions.assertThat;
import static org.joda.time.DateTimeZone.UTC;

import org.assertj.time.api.JodaTimeBaseTest;
import org.joda.time.DateTime;
import org.junit.Test;


public class ZonedDateTimeAssert_isEqualToIgnoringMinutes_Test extends JodaTimeBaseTest {

  private final DateTime refDatetime = new DateTime(2000, 1, 1, 23, 0, 0, 0, UTC);

  @Test
  public void should_pass_if_actual_is_equal_to_other_ignoring_minute_fields() {
    assertThat(refDatetime).isEqualToIgnoringMinutes(refDatetime.plusMinutes(1));
  }

  @Test
  public void should_fail_if_actual_is_not_equal_to_given_datetime_with_minute_ignored() {
    try {
      assertThat(refDatetime).isEqualToIgnoringMinutes(refDatetime.minusMinutes(1));
    } catch (AssertionError e) {
      assertThat(e.getMessage())
          .isEqualTo(
              "\nExpecting:\n  <2000-01-01T23:00:00.000Z>\nto have same year, month, day and hour as:\n  <2000-01-01T22:59:00.000Z>\nbut had not.");
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_as_minutes_fields_are_different_even_if_time_difference_is_less_than_a_minute() {
    try {
      assertThat(refDatetime).isEqualToIgnoringMinutes(refDatetime.minusMillis(1));
    } catch (AssertionError e) {
      assertThat(e.getMessage())
          .isEqualTo(
              "\nExpecting:\n  <2000-01-01T23:00:00.000Z>\nto have same year, month, day and hour as:\n  <2000-01-01T22:59:59.999Z>\nbut had not.");
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectException(AssertionError.class, actualIsNull());
    DateTime actual = null;
    assertThat(actual).isEqualToIgnoringMinutes(new DateTime());
  }

  @Test
  public void should_throw_error_if_given_datetime_is_null() {
    expectIllegalArgumentException(NULL_DATE_TIME_PARAMETER_MESSAGE);
    assertThat(refDatetime).isEqualToIgnoringMinutes(null);
  }

}
