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
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.time.api.Assertions.assertThat;

import static org.joda.time.DateTimeZone.UTC;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author Paweł Stawicki
 * @author Joel Costigliola
 */
@RunWith(Theories.class)
public class DateTimeAssert_isBeforeOrEqualTo_Test extends DateTimeAssertBaseTest {

  @Theory
  public void test_isBeforeOrEqual_assertion(DateTime referenceDate, DateTime dateBefore, DateTime dateAfter) {
    // GIVEN
    testAssumptions(referenceDate, dateBefore, dateAfter);
    // WHEN
    assertThat(dateBefore).isBeforeOrEqualTo(referenceDate);
    assertThat(dateBefore).isBeforeOrEqualTo(referenceDate.toString());
    assertThat(referenceDate).isBeforeOrEqualTo(referenceDate);
    assertThat(referenceDate).isBeforeOrEqualTo(referenceDate.toString());
    // THEN
    verify_that_isBeforeOrEqual_assertion_fails_and_throws_AssertionError(dateAfter, referenceDate);
  }

  @Test
  public void isBeforeOrEqualTo_should_compare_datetimes_in_actual_timezone() {
    DateTime utcDateTime = new DateTime(2013, 6, 10, 0, 0, DateTimeZone.UTC);
    DateTimeZone cestTimeZone = DateTimeZone.forID("Europe/Berlin");
    DateTime cestDateTime1 = new DateTime(2013, 6, 10, 2, 0, cestTimeZone);
    DateTime cestDateTime2 = new DateTime(2013, 6, 10, 3, 0, cestTimeZone);
    // utcDateTime = cestDateTime1
    assertThat(utcDateTime).as("in UTC time zone").isBeforeOrEqualTo(cestDateTime1);
    //  utcDateTime < cestDateTime2  
    assertThat(utcDateTime).as("in UTC time zone").isBeforeOrEqualTo(cestDateTime2);
  }

  @Test
  public void test_isBeforeOrEqual_assertion_error_message() {
    try {
      assertThat(new DateTime(2000, 1, 5, 3, 0, 5, UTC)).isBeforeOrEqualTo(new DateTime(1998, 1, 1, 3, 3, 3, UTC));
    } catch (AssertionError e) {
      assertThat(e).hasMessage("\nExpecting:\n  <2000-01-05T03:00:05.000Z>\nto be before or equals to:\n  <1998-01-01T03:03:03.000Z>\n");
      return;
    }
    fail("Should have thrown AssertionError");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectException(AssertionError.class, actualIsNull());
    DateTime actual = null;
    assertThat(actual).isBeforeOrEqualTo(new DateTime());
  }

  @Test
  public void should_fail_if_dateTime_parameter_is_null() {
    expectException(IllegalArgumentException.class, "The DateTime to compare actual with should not be null");
    assertThat(new DateTime()).isBeforeOrEqualTo((DateTime) null);
  }

  @Test
  public void should_fail_if_dateTime_as_string_parameter_is_null() {
    expectException(IllegalArgumentException.class,
        "The String representing the DateTime to compare actual with should not be null");
    assertThat(new DateTime()).isBeforeOrEqualTo((String) null);
  }

  private static void verify_that_isBeforeOrEqual_assertion_fails_and_throws_AssertionError(DateTime dateToCheck,
      DateTime reference) {
    try {
      assertThat(dateToCheck).isBeforeOrEqualTo(reference);
    } catch (AssertionError e) {
      // AssertionError was expected, test same assertion with String based parameter
      try {
        assertThat(dateToCheck).isBeforeOrEqualTo(reference.toString());
      } catch (AssertionError e2) {
        // AssertionError was expected (again)
        return;
      }
    }
    fail("Should have thrown AssertionError");
  }

}
