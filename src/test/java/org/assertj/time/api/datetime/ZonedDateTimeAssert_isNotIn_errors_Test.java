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

import org.junit.*;
import org.junit.experimental.theories.*;
import org.junit.runner.*;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.time.api.Assertions.assertThat;

/**
 * Only test String based assertion (tests with {@link ZonedDateTime} are already defined in assertj-core)
 * 
 * @author Joel Costigliola
 */
@RunWith(Theories.class)
public class ZonedDateTimeAssert_isNotIn_errors_Test extends DateTimeAssertBaseTest {

  @Theory
  public void test_isNotIn_assertion( ZonedDateTime referenceDate ) {
    // WHEN
    assertThat( referenceDate ).isNotIn( referenceDate.plus( 1, ChronoUnit.MILLIS ).toString(), referenceDate.plus( 2, ChronoUnit.MILLIS ).toString() );
    // THEN
    verify_that_isNotIn_assertion_fails_and_throws_AssertionError(referenceDate);
  }

  @Test
  public void test_isNotIn_assertion_error_message() {
    try {
      assertThat( ZonedDateTime.of( 2000, 1, 5, 3, 0, 5, 0, ZoneOffset.UTC ) ).isNotIn( ZonedDateTime.of( 2000, 1, 5, 3, 0, 5, 0, ZoneOffset.UTC ).toString(),
                                                                                        ZonedDateTime.of( 2012, 1, 1, 3, 3, 3, 0, ZoneOffset.UTC ).toString() );
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
    expectException( IllegalArgumentException.class, "The given ZonedDateTime array should not be null" );
    assertThat( ZonedDateTime.now() ).isNotIn( ( String[] ) null );
  }

  @Test
  public void should_fail_if_dateTimes_as_string_array_parameter_is_empty() {
    expectException( IllegalArgumentException.class, "The given ZonedDateTime array should not be empty" );
    assertThat( ZonedDateTime.now() ).isNotIn( new String[0] );
  }

  private static void verify_that_isNotIn_assertion_fails_and_throws_AssertionError( ZonedDateTime reference ) {
    try {
      assertThat(reference).isNotIn(reference.toString(), reference.plus(1, ChronoUnit.MILLIS).toString());
    } catch (AssertionError e) {
      // AssertionError was expected
      return;
    }
    fail("Should have thrown AssertionError");
  }

}
