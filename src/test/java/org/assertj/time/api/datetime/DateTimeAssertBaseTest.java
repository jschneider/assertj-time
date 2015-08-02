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

import org.assertj.time.api.JodaTimeBaseTest;
import org.assertj.time.api.ZonedDateTimeAssert;
import org.junit.experimental.theories.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assume.*;


/**
 * Base test class for {@link ZonedDateTimeAssert} tests.
 * 
 * @author Paweł Stawicki
 * @author Joel Costigliola
 */
public class DateTimeAssertBaseTest extends JodaTimeBaseTest {

  @DataPoint
  public static ZonedDateTime dateTime1 = ZonedDateTime.of(2000, 12, 14, 0, 00, 0, 0, ZoneId.of("Africa/Banjul"));
  @DataPoint
  public static ZonedDateTime dateTime2 = ZonedDateTime.of(2000, 12, 13, 23, 59, 59, 999000000, ZoneId.of("Africa/Banjul"));
  @DataPoint
  public static ZonedDateTime dateTime3 = ZonedDateTime.of(2000, 12, 14, 0, 0, 0, 1000000, ZoneId.of("Africa/Banjul"));
  @DataPoint
  public static ZonedDateTime dateTime4 = ZonedDateTime.of(2000, 12, 14, 22, 15, 15, 875000000, ZoneId.of("Africa/Banjul"));
  @DataPoint
  public static ZonedDateTime dateTime5 = ZonedDateTime.of(2000, 12, 14, 22, 15, 15, 874000000, ZoneId.of("Africa/Banjul"));
  @DataPoint
  public static ZonedDateTime dateTime6 = ZonedDateTime.of(2000, 12, 14, 22, 15, 15, 876000000, ZoneId.of("Africa/Banjul"));

  protected static void testAssumptions(ZonedDateTime reference, ZonedDateTime dateBefore, ZonedDateTime dateAfter) {
    assumeTrue(dateBefore.isBefore(reference));
    assumeTrue(dateAfter.isAfter(reference));
  }

}