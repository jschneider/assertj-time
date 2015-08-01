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
package org.assertj.time.api;

import static org.assertj.time.error.ShouldBeAfter.shouldBeAfter;
import static org.assertj.time.error.ShouldBeAfterOrEqualsTo.shouldBeAfterOrEqualsTo;
import static org.assertj.time.error.ShouldBeBefore.shouldBeBefore;
import static org.assertj.time.error.ShouldBeBeforeOrEqualsTo.shouldBeBeforeOrEqualsTo;
import static org.assertj.time.error.ShouldBeEqualIgnoringHours.shouldBeEqualIgnoringHours;
import static org.assertj.time.error.ShouldBeEqualIgnoringMillis.shouldBeEqualIgnoringMillis;
import static org.assertj.time.error.ShouldBeEqualIgnoringMinutes.shouldBeEqualIgnoringMinutes;
import static org.assertj.time.error.ShouldBeEqualIgnoringSeconds.shouldBeEqualIgnoringSeconds;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;

import java.time.ZonedDateTime;

/**
 * Assertions for {@link ZonedDateTime} type.
 *
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 * @author Paweł Stawicki
 * @author Joel Costigliola
 */
public class ZonedDateTimeAssert extends AbstractAssert<ZonedDateTimeAssert, ZonedDateTime> {

  public static final String NULL_DATE_TIME_PARAMETER_MESSAGE = "The ZonedDateTime to compare actual with should not be null";

  /**
   * Creates a new <code>{@link ZonedDateTimeAssert}</code>.
   * 
   * @param selfType the "self type"
   * @param actual the actual value to verify
   */
  protected ZonedDateTimeAssert( Class<ZonedDateTimeAssert> selfType, ZonedDateTime actual) {
    super(actual, selfType);
  }

  // visible for test
  protected ZonedDateTime getActual() {
    return actual;
  }

  /**
   * Verifies that the actual {@code ZonedDateTime} is <b>strictly</b> before the given one.
   * <p>
   * Example :
   * 
   * <pre>
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isBefore(new ZonedDateTime(&quot;2000-01-02&quot;));
   * </pre>
   * 
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not strictly before the given one.
   */
  public ZonedDateTimeAssert isBefore( ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertZonedDateTimeParameterIsNotNull(other);
    if (!actual.isBefore(other)) {
      throw Failures.instance().failure(info, shouldBeBefore(actual, other));
    }
    return this;
  }

  /**
   * Same assertion as {@link #isBefore(ZonedDateTime)} but the {@link ZonedDateTime} is built from given String, which must
   * follow <a
   * href="http://joda-time.sourceforge.net/api-release/org/joda/time/format/ISOZonedDateTimeFormat.html#ZonedDateTimeParser()"
   * >ISO ZonedDateTime format</a> to allow calling {@link ZonedDateTime#ZonedDateTime(Object) ZonedDateTime(Object)} constructor.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link ZonedDateTimeZone} of the
   * {@link ZonedDateTime} to check..
   * <p>
   * Example :
   * 
   * <pre>
   * // use directly String in comparison to avoid a conversion
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isBefore(&quot;2000-01-02&quot;);
   * </pre>
   * 
   * @param ZonedDateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not strictly before the {@link ZonedDateTime} built from given
   *           String.
   */
  public ZonedDateTimeAssert isBefore( String ZonedDateTimeAsString) {
    assertZonedDateTimeAsStringParameterIsNotNull(ZonedDateTimeAsString);
    //return isBefore(new ZonedDateTime(ZonedDateTimeAsString, actual.getZone()));
    return isBefore( ZonedDateTime.parse( ZonedDateTimeAsString ) );
  }

  /**
   * Verifies that the actual {@code ZonedDateTime} is before or equals to the given one.
   * <p>
   * Example :
   * 
   * <pre>
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isBeforeOrEqualTo(new ZonedDateTime(&quot;2000-01-01&quot;))
   *                                       .isBeforeOrEqualTo(new ZonedDateTime(&quot;2000-01-02&quot;));
   * </pre>
   * 
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not before or equals to the given one.
   */
  public ZonedDateTimeAssert isBeforeOrEqualTo( ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertZonedDateTimeParameterIsNotNull(other);
    if (actual.isAfter(other)) {
      throw Failures.instance().failure(info, shouldBeBeforeOrEqualsTo(actual, other));
    }
    return this;
  }

  /**
   * Same assertion as {@link #isBeforeOrEqualTo(ZonedDateTime)} but the {@link ZonedDateTime} is built from given String, which
   * must follow <a
   * href="http://joda-time.sourceforge.net/api-release/org/joda/time/format/ISOZonedDateTimeFormat.html#ZonedDateTimeParser()"
   * >ISO ZonedDateTime format</a> to allow calling {@link ZonedDateTime#ZonedDateTime(Object) ZonedDateTime(Object)} constructor.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link ZonedDateTimeZone} of the
   * {@link ZonedDateTime} to check..
   * <p>
   * Example :
   * 
   * <pre>
   * // use String in comparison to avoid conversion
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isBeforeOrEqualTo(&quot;2000-01-01&quot;)
   *                                       .isBeforeOrEqualTo(&quot;2000-01-02&quot;);
   * </pre>
   * 
   * @param zonedDateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not before or equals to the {@link ZonedDateTime} built from
   *           given String.
   */
  public ZonedDateTimeAssert isBeforeOrEqualTo( String zonedDateTimeAsString) {
    assertZonedDateTimeAsStringParameterIsNotNull(zonedDateTimeAsString);
    return isBeforeOrEqualTo(ZonedDateTime.parse(zonedDateTimeAsString));
  }

  /**
   * Verifies that the actual {@code ZonedDateTime} is after or equals to the given one.
   * <p>
   * Example :
   * 
   * <pre>
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isAfterOrEqualTo(new ZonedDateTime(&quot;2000-01-01&quot;))
   *                                       .isAfterOrEqualTo(new ZonedDateTime(&quot;1999-12-31&quot;));
   * </pre>
   * 
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not after or equals to the given one.
   */
  public ZonedDateTimeAssert isAfterOrEqualTo( ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertZonedDateTimeParameterIsNotNull(other);
    if (actual.isBefore(other)) {
      throw Failures.instance().failure(info, shouldBeAfterOrEqualsTo(actual, other));
    }
    return this;
  }

  /**
   * Same assertion as {@link #isAfterOrEqualTo(ZonedDateTime)} but the {@link ZonedDateTime} is built from given String, which
   * must follow <a
   * href="http://joda-time.sourceforge.net/api-release/org/joda/time/format/ISOZonedDateTimeFormat.html#ZonedDateTimeParser()"
   * >ISO ZonedDateTime format</a> to allow calling {@link ZonedDateTime#ZonedDateTime(Object) ZonedDateTime(Object)} constructor.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link ZonedDateTimeZone} of the
   * {@link ZonedDateTime} to check.
   * <p>
   * Example :
   * 
   * <pre>
   * // use String in comparison to avoid conversion
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isAfterOrEqualTo(&quot;2000-01-01&quot;)
   *                                       .isAfterOrEqualTo(&quot;1999-12-31&quot;);
   * </pre>
   * 
   * @param zonedDateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not after or equals to the {@link ZonedDateTime} built from
   *           given String.
   */
  public ZonedDateTimeAssert isAfterOrEqualTo( String zonedDateTimeAsString) {
    assertZonedDateTimeAsStringParameterIsNotNull(zonedDateTimeAsString);
    //return isAfterOrEqualTo(new ZonedDateTime(ZonedDateTimeAsString, actual.getZone()));
    return isAfterOrEqualTo(ZonedDateTime.parse( zonedDateTimeAsString ));
  }

  /**
   * Verifies that the actual {@code ZonedDateTime} is <b>strictly</b> after the given one.
   * <p>
   * Example :
   * 
   * <pre>
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isAfter(new ZonedDateTime(&quot;1999-12-31&quot;));
   * </pre>
   * 
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not strictly after the given one.
   */
  public ZonedDateTimeAssert isAfter( ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertZonedDateTimeParameterIsNotNull(other);
    if (!actual.isAfter(other)) {
      throw Failures.instance().failure(info, shouldBeAfter(actual, other));
    }
    return this;
  }

  /**
   * Same assertion as {@link #isAfter(ZonedDateTime)} but the {@link ZonedDateTime} is built from given String, which must follow
   * <a href="http://joda-time.sourceforge.net/api-release/org/joda/time/format/ISOZonedDateTimeFormat.html#ZonedDateTimeParser()"
   * >ISO ZonedDateTime format</a> to allow calling {@link ZonedDateTime#ZonedDateTime(Object) ZonedDateTime(Object)} constructor.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link ZonedDateTimeZone} of the
   * {@link ZonedDateTime} to check.
   * <p>
   * Example :
   * 
   * <pre>
   * // use String in comparison to avoid conversion
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isAfter(&quot;1999-12-31&quot;);
   * </pre>
   * 
   * @param zonedDateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not strictly after the {@link ZonedDateTime} built from given
   *           String.
   */
  public ZonedDateTimeAssert isAfter( String zonedDateTimeAsString) {
    assertZonedDateTimeAsStringParameterIsNotNull(zonedDateTimeAsString);
    return isAfter(ZonedDateTime.parse( zonedDateTimeAsString ));
  }

  /**
   * Verifies that actual and given {@code ZonedDateTime} have same year, month, day, hour, minute and second fields,
   * (millisecond fields are ignored in comparison).
   * <p>
   * Note that given {@link ZonedDateTime} is converted in the actual's {@link ZonedDateTimeZone} before comparison.
   * <p>
   * Assertion can fail with ZonedDateTimes in same chronological millisecond time window, e.g :
   * <p>
   * 2000-01-01T00:00:<b>01.000</b> and 2000-01-01T00:00:<b>00.999</b>.
   * <p>
   * Assertion fails as second fields differ even if time difference is only 1ms.
   * <p>
   * Code example :
   * 
   * <pre>
   * // successfull assertions
   * ZonedDateTime ZonedDateTime1 = new ZonedDateTime(2000, 1, 1, 0, 0, 1, 0);
   * ZonedDateTime ZonedDateTime2 = new ZonedDateTime(2000, 1, 1, 0, 0, 1, 456);
   * assertThat(ZonedDateTime1).isEqualToIgnoringMillis(ZonedDateTime2);
   * 
   * // failing assertions (even if time difference is only 1ms)
   * ZonedDateTime ZonedDateTimeA = new ZonedDateTime(2000, 1, 1, 0, 0, 1, 0);
   * ZonedDateTime ZonedDateTimeB = new ZonedDateTime(2000, 1, 1, 0, 0, 0, 999);
   * assertThat(ZonedDateTimeA).isEqualToIgnoringMillis(ZonedDateTimeB);
   * </pre>
   * 
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is are not equal with milliseconds ignored.
   */
  public ZonedDateTimeAssert isEqualToIgnoringMillis( ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertZonedDateTimeParameterIsNotNull(other);
    if (!areEqualIgnoringMillis(actual, other.withZoneSameInstant(actual.getZone()))) {
      throw Failures.instance().failure(info, shouldBeEqualIgnoringMillis(actual, other));
    }
    return this;
  }

  /**
   * Verifies that actual and given {@link ZonedDateTime} have same year, month, day, hour and minute fields (second and
   * millisecond fields are ignored in comparison).
   * <p>
   * Note that given {@link ZonedDateTime} is converted in the actual's {@link ZonedDateTimeZone} before comparison.
   * <p>
   * Assertion can fail with ZonedDateTimes in same chronological second time window, e.g :
   * <p>
   * 2000-01-01T00:<b>01:00</b>.000 and 2000-01-01T00:<b>00:59</b>.000.
   * <p>
   * Assertion fails as minute fields differ even if time difference is only 1s.
   * <p>
   * Code example :
   * 
   * <pre>
   * // successfull assertions
   * ZonedDateTime ZonedDateTime1 = new ZonedDateTime(2000, 1, 1, 23, 50, 0, 0);
   * ZonedDateTime ZonedDateTime2 = new ZonedDateTime(2000, 1, 1, 23, 50, 10, 456);
   * assertThat(ZonedDateTime1).isEqualToIgnoringSeconds(ZonedDateTime2);
   * 
   * // failing assertions (even if time difference is only 1ms)
   * ZonedDateTime ZonedDateTimeA = new ZonedDateTime(2000, 1, 1, 23, 50, 00, 000);
   * ZonedDateTime ZonedDateTimeB = new ZonedDateTime(2000, 1, 1, 23, 49, 59, 999);
   * assertThat(ZonedDateTimeA).isEqualToIgnoringSeconds(ZonedDateTimeB);
   * </pre>
   * 
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is are not equal with second and millisecond fields ignored.
   */
  public ZonedDateTimeAssert isEqualToIgnoringSeconds( ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertZonedDateTimeParameterIsNotNull(other);
    if (!areEqualIgnoringSeconds(actual, other.withZoneSameInstant(actual.getZone()))) {
      throw Failures.instance().failure(info, shouldBeEqualIgnoringSeconds(actual, other));
    }
    return this;
  }

  /**
   * Verifies that actual and given {@code ZonedDateTime} have same year, month, day and hour fields (minute, second and
   * millisecond fields are ignored in comparison).
   * <p>
   * Note that given {@link ZonedDateTime} is converted in the actual's {@link ZonedDateTimeZone} before comparison.
   * <p>
   * Assertion can fail with ZonedDateTimes in same chronological second time window, e.g :
   * <p>
   * 2000-01-01T<b>01:00</b>:00.000 and 2000-01-01T<b>00:59:59</b>.000.
   * <p>
   * Time difference is only 1s but hour fields differ.
   * <p>
   * Code example :
   * 
   * <pre>
   * // successfull assertions
   * ZonedDateTime ZonedDateTime1 = new ZonedDateTime(2000, 1, 1, 23, 50, 0, 0);
   * ZonedDateTime ZonedDateTime2 = new ZonedDateTime(2000, 1, 1, 23, 00, 2, 7);
   * assertThat(ZonedDateTime1).isEqualToIgnoringMinutes(ZonedDateTime2);
   * 
   * // failing assertions (even if time difference is only 1ms)
   * ZonedDateTime ZonedDateTimeA = new ZonedDateTime(2000, 1, 1, 01, 00, 00, 000);
   * ZonedDateTime ZonedDateTimeB = new ZonedDateTime(2000, 1, 1, 00, 59, 59, 999);
   * assertThat(ZonedDateTimeA).isEqualToIgnoringMinutes(ZonedDateTimeB);
   * </pre>
   * 
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is are not equal ignoring minute, second and millisecond
   *           fields.
   */
  public ZonedDateTimeAssert isEqualToIgnoringMinutes( ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertZonedDateTimeParameterIsNotNull(other);
    if (!areEqualIgnoringMinutes(actual, other.withZoneSameInstant(actual.getZone()))) {
      throw Failures.instance().failure(info, shouldBeEqualIgnoringMinutes(actual, other));
    }
    return this;
  }

  /**
   * Verifies that actual and given {@code ZonedDateTime} have same year, month and day fields (hour, minute, second and
   * millisecond fields are ignored in comparison).
   * <p>
   * Note that given {@link ZonedDateTime} is converted in the actual's {@link ZonedDateTimeZone} before comparison.
   * <p>
   * Assertion can fail with ZonedDateTimes in same chronological minute time window, e.g :
   * <p>
   * 2000-01-<b>01T23:59</b>:00.000 and 2000-01-02T<b>00:00</b>:00.000.
   * <p>
   * Time difference is only 1min but day fields differ.
   * <p>
   * Code example :
   * 
   * <pre>
   * // successfull assertions
   * ZonedDateTime ZonedDateTime1 = new ZonedDateTime(2000, 1, 1, 23, 59, 59, 999);
   * ZonedDateTime ZonedDateTime2 = new ZonedDateTime(2000, 1, 1, 00, 00, 00, 000);
   * assertThat(ZonedDateTime1).isEqualToIgnoringHours(ZonedDateTime2);
   * 
   * // failing assertions (even if time difference is only 1ms)
   * ZonedDateTime ZonedDateTimeA = new ZonedDateTime(2000, 1, 2, 00, 00, 00, 000);
   * ZonedDateTime ZonedDateTimeB = new ZonedDateTime(2000, 1, 1, 23, 59, 59, 999);
   * assertThat(ZonedDateTimeA).isEqualToIgnoringHours(ZonedDateTimeB);
   * </pre>
   * 
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is are not equal with second and millisecond fields ignored.
   */
  public ZonedDateTimeAssert isEqualToIgnoringHours( ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertZonedDateTimeParameterIsNotNull(other);
    if (!haveSameYearMonthAndDayOfMonth(actual, other.withZoneSameInstant(actual.getZone()))) {
      throw Failures.instance().failure(info, shouldBeEqualIgnoringHours(actual, other));
    }
    return this;
  }

  /**
   * Verifies that the actual {@link ZonedDateTime} is equal to the given one <b>in the actual's {@link ZonedDateTimeZone}</b>.
   * <p>
   * Example :
   * 
   * <pre>
   * // use directly String in comparison to avoid a conversion
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isEqualTo(new ZonedDateTime(&quot;2000-01-01&quot;));
   * </pre>
   * 
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not equal to the {@link ZonedDateTime} in the actual ZonedDateTime's
   *           ZonedDateTimeZone.
   */
  public ZonedDateTimeAssert isEqualTo( ZonedDateTime expected) {
    return super.isEqualTo( expected.withZoneSameInstant( actual.getZone() ) );
  }

  /**
   * Same assertion as {@link #isEqualTo(ZonedDateTime)} but the {@link ZonedDateTime} is built from given String, which must
   * follow <a
   * href="http://joda-time.sourceforge.net/api-release/org/joda/time/format/ISOZonedDateTimeFormat.html#ZonedDateTimeParser()"
   * >ISO ZonedDateTime format</a> to allow calling {@link ZonedDateTime#ZonedDateTime(Object) ZonedDateTime(Object)} constructor.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link ZonedDateTimeZone} of the
   * {@link ZonedDateTime} to check.
   * <p>
   * Example :
   * 
   * <pre>
   * // use directly String in comparison to avoid a conversion
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isEqualTo(&quot;2000-01-01&quot;);
   * </pre>
   * 
   * @param zonedDateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not equal to the {@link ZonedDateTime} built from given String.
   */
  public ZonedDateTimeAssert isEqualTo( String zonedDateTimeAsString) {
    assertZonedDateTimeAsStringParameterIsNotNull(zonedDateTimeAsString);
    return isEqualTo(ZonedDateTime.parse(zonedDateTimeAsString));
  }

  /**
   * Verifies that the actual value is not equal to the given one <b>in the actual ZonedDateTime's ZonedDateTimeZone</b>.
   * <p>
   * Example :
   * 
   * <pre>
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isNotEqualTo(new ZonedDateTime(&quot;2000-01-15&quot;));
   * </pre>
   * 
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is equal to the {@link ZonedDateTime} in the actual ZonedDateTime's
   *           ZonedDateTimeZone.
   */
  public ZonedDateTimeAssert isNotEqualTo( ZonedDateTime expected) {
    return super.isNotEqualTo(expected);
  }

  /**
   * Same assertion as {@link #isNotEqualTo(ZonedDateTime)} but the {@link ZonedDateTime} is built from given String, which must
   * follow <a
   * href="http://joda-time.sourceforge.net/api-release/org/joda/time/format/ISOZonedDateTimeFormat.html#ZonedDateTimeParser()"
   * >ISO ZonedDateTime format</a> to allow calling {@link ZonedDateTime#ZonedDateTime(Object) ZonedDateTime(Object)} constructor.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link ZonedDateTimeZone} of the
   * {@link ZonedDateTime} to check.. {@link ZonedDateTime}.
   * <p>
   * Example :
   * 
   * <pre>
   * // use directly String in comparison to avoid a conversion
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isNotEqualTo(&quot;2000-01-15&quot;);
   * </pre>
   * 
   * @param zonedDateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is equal to the {@link ZonedDateTime} built from given String.
   */
  public ZonedDateTimeAssert isNotEqualTo( String zonedDateTimeAsString) {
    assertZonedDateTimeAsStringParameterIsNotNull(zonedDateTimeAsString);
    return isNotEqualTo(ZonedDateTime.parse(zonedDateTimeAsString));
  }

  /**
   * Verifies that the actual {@link ZonedDateTime} is equal to one of the given {@link ZonedDateTime} <b>in the actual ZonedDateTime's
   * {@link ZonedDateTimeZone}</b>.
   * <p>
   * Example :
   * 
   * <pre>
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isIn(new ZonedDateTime(&quot;1999-12-31&quot;), new ZonedDateTime(&quot;2000-01-01&quot;));
   * </pre>
   * 
   * @param expected the given {@link ZonedDateTime}s to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not in the given {@link ZonedDateTime}s.
   */
  public ZonedDateTimeAssert isIn( ZonedDateTime... expected) {
    return super.isIn((Object[]) changeToActualTimeZone(expected));
  }

  /**
   * Same assertion as {@link #isIn(ZonedDateTime...)} but the {@link ZonedDateTime} are built from given String, which must
   * follow <a
   * href="http://joda-time.sourceforge.net/api-release/org/joda/time/format/ISOZonedDateTimeFormat.html#ZonedDateTimeParser()"
   * >ISO ZonedDateTime format</a> to allow calling {@link ZonedDateTime#ZonedDateTime(Object) ZonedDateTime(Object)} constructor.
   * <p>
   * Note that the {@link ZonedDateTime}s created from the given Strings are built in the {@link ZonedDateTimeZone} of the
   * {@link ZonedDateTime} to check..
   * <p>
   * Example :
   * 
   * <pre>
   * // use String based representation of LocalZonedDateTime
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isIn(&quot;1999-12-31&quot;, &quot;2000-01-01&quot;);
   * </pre>
   * 
   * @param ZonedDateTimesAsString String array representing {@link ZonedDateTime}s.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not in the {@link ZonedDateTime}s built from given Strings.
   */
  public ZonedDateTimeAssert isIn( String... ZonedDateTimesAsString) {
    checkIsNotNullAndNotEmpty(ZonedDateTimesAsString);
    return isIn(convertToZonedDateTimeArray(ZonedDateTimesAsString));
  }

  /**
   * Verifies that the actual {@link ZonedDateTime} is equal to one of the given {@link ZonedDateTime} <b>in the actual ZonedDateTime's
   * {@link ZonedDateTimeZone}</b>.
   * <p>
   * Example :
   * 
   * <pre>
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isNotIn(new ZonedDateTime(&quot;1999-12-31&quot;), new ZonedDateTime(&quot;2000-01-02&quot;));
   * </pre>
   * 
   * @param expected the given {@link ZonedDateTime}s to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not in the given {@link ZonedDateTime}s.
   */
  public ZonedDateTimeAssert isNotIn( ZonedDateTime... expected) {
    return super.isNotIn((Object[]) changeToActualTimeZone(expected));
  }

  /**
   * Same assertion as {@link #isNotIn(org.joda.time.ZonedDateTime...)} but the {@link ZonedDateTime} is built from given String,
   * which must follow <a
   * href="http://joda-time.sourceforge.net/api-release/org/joda/time/format/ISOZonedDateTimeFormat.html#ZonedDateTimeParser()"
   * >ISO ZonedDateTime format</a> to allow calling {@link ZonedDateTime#ZonedDateTime(Object) ZonedDateTime(Object)} constructor.
   * <p>
   * Note that the {@link ZonedDateTime}s created from the given Strings are built in the {@link ZonedDateTimeZone} of the
   * {@link ZonedDateTime} to check..
   * <p>
   * Example :
   * 
   * <pre>
   * // use String based representation of ZonedDateTime
   * assertThat(new ZonedDateTime(&quot;2000-01-01&quot;)).isNotIn(&quot;1999-12-31&quot;, &quot;2000-01-02&quot;);
   * </pre>
   * 
   * @param ZonedDateTimesAsString String array representing {@link ZonedDateTime}s.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not equal to the {@link ZonedDateTime} built from given String.
   */
  public ZonedDateTimeAssert isNotIn( String... ZonedDateTimesAsString) {
    checkIsNotNullAndNotEmpty(ZonedDateTimesAsString);
    return isNotIn(convertToZonedDateTimeArray(ZonedDateTimesAsString));
  }

  private ZonedDateTime[] convertToZonedDateTimeArray(String... zonedDateTimesAsString) {
    ZonedDateTime[] dates = new ZonedDateTime[zonedDateTimesAsString.length];
    for (int i = 0; i < zonedDateTimesAsString.length; i++) {
      // building the ZonedDateTime in actual's ZonedDateTimeZone
      dates[i] = ZonedDateTime.parse(zonedDateTimesAsString[i]);
    }
    return dates;
  }

  private ZonedDateTime[] changeToActualTimeZone(ZonedDateTime... ZonedDateTimes) {
    ZonedDateTime[] dates = new ZonedDateTime[ZonedDateTimes.length];
    for (int i = 0; i < ZonedDateTimes.length; i++) {
      // building the ZonedDateTime in actual's ZonedDateTimeZone
      dates[i] = ZonedDateTimes[i];
    }
    return dates;
  }

  private void checkIsNotNullAndNotEmpty(Object[] values) {
    if (values == null) {
      throw new IllegalArgumentException("The given ZonedDateTime array should not be null");
    }
    if (values.length == 0) {
      throw new IllegalArgumentException("The given ZonedDateTime array should not be empty");
    }
  }

  /**
   * Check that the {@link ZonedDateTime} to compare actual {@link ZonedDateTime} to is not null, otherwise throws a
   * {@link IllegalArgumentException} with an explicit message
   * 
   * @param ZonedDateTime the {@link ZonedDateTime} to check
   * @throws {@link IllegalArgumentException} with an explicit message if the given {@link ZonedDateTime} is null
   */
  private static void assertZonedDateTimeParameterIsNotNull(ZonedDateTime ZonedDateTime) {
    if (ZonedDateTime == null) {
      throw new IllegalArgumentException(NULL_DATE_TIME_PARAMETER_MESSAGE);
    }
  }

  /**
   * Check that the {@link ZonedDateTime} string representation to compare actual {@link ZonedDateTime} to is not null, otherwise
   * throws a {@link IllegalArgumentException} with an explicit message
   * 
   * @param ZonedDateTimeAsString String representing the ZonedDateTime to compare actual with
   * @throws {@link IllegalArgumentException} with an explicit message if the given {@link String} is null
   */
  private static void assertZonedDateTimeAsStringParameterIsNotNull(String ZonedDateTimeAsString) {
    if (ZonedDateTimeAsString == null) {
      throw new IllegalArgumentException(
                                         "The String representing the ZonedDateTime to compare actual with should not be null");
    }
  }

  /**
   * Returns true if both ZonedDateTime are in the same year, month and day of month, hour, minute and second, false
   * otherwise.
   * 
   * @param actual the actual ZonedDateTime. expected not be null
   * @param other the other ZonedDateTime. expected not be null
   * @return true if both ZonedDateTime are in the same year, month and day of month, hour, minute and second, false
   *         otherwise.
   */
  private static boolean areEqualIgnoringMillis(ZonedDateTime actual, ZonedDateTime other) {
    return areEqualIgnoringSeconds(actual, other) && actual.getSecond() == other.getSecond();
  }

  /**
   * Returns true if both ZonedDateTime are in the same year, month, day of month, hour and minute, false otherwise.
   * 
   * @param actual the actual ZonedDateTime. expected not be null
   * @param other the other ZonedDateTime. expected not be null
   * @return true if both ZonedDateTime are in the same year, month, day of month, hour and minute, false otherwise.
   */
  private static boolean areEqualIgnoringSeconds(ZonedDateTime actual, ZonedDateTime other) {
    return areEqualIgnoringMinutes(actual, other) && actual.getMinute() == other.getMinute();
  }

  /**
   * Returns true if both ZonedDateTime are in the same year, month, day of month and hour, false otherwise.
   * 
   * @param actual the actual ZonedDateTime. expected not be null
   * @param other the other ZonedDateTime. expected not be null
   * @return true if both ZonedDateTime are in the same year, month, day of month and hour, false otherwise.
   */
  private static boolean areEqualIgnoringMinutes(ZonedDateTime actual, ZonedDateTime other) {
    return haveSameYearMonthAndDayOfMonth(actual, other) && actual.getHour() == other.getHour();
  }

  /**
   * Returns true if both ZonedDateTime are in the same year, month and day of month, false otherwise.
   * 
   * @param actual the actual ZonedDateTime. expected not be null
   * @param other the other ZonedDateTime. expected not be null
   * @return true if both ZonedDateTime are in the same year, month and day of month, false otherwise
   */
  private static boolean haveSameYearMonthAndDayOfMonth(ZonedDateTime actual, ZonedDateTime other) {
    return haveSameYearAndMonth(actual, other) && actual.getDayOfMonth() == other.getDayOfMonth();
  }

  /**
   * Returns true if both ZonedDateTime are in the same year and month, false otherwise.
   * 
   * @param actual the actual ZonedDateTime. expected not be null
   * @param other the other ZonedDateTime. expected not be null
   * @return true if both ZonedDateTime are in the same year and month, false otherwise
   */
  private static boolean haveSameYearAndMonth(ZonedDateTime actual, ZonedDateTime other) {
    return haveSameYear(actual, other) && actual.getMonth() == other.getMonth();
  }

  /**
   * Returns true if both ZonedDateTime are in the same year, false otherwise.
   * 
   * @param actual the actual ZonedDateTime. expected not be null
   * @param other the other ZonedDateTime. expected not be null
   * @return true if both ZonedDateTime are in the same year, false otherwise
   */
  private static boolean haveSameYear(ZonedDateTime actual, ZonedDateTime other) {
    return actual.getYear() == other.getYear();
  }

}
