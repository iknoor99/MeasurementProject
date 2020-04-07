/*
 * Copyright 1999-2004 The Apache Software Foundation
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
 */
package org.apache.commons.collections;

import java.util.Map;

import junit.framework.*;

/**
 * Test cases for BeanMap
 * 
 * @author <a href="mailto:morgand@apache.org">Morgan Delagrange</a>
 */
public class TestBeanMap extends TestMap {

    public TestBeanMap(String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(TestBeanMap.class);
    }

/*
  note to self.  The getter and setter methods were generated by copying the
  field declarations and using the following regular expression search and
  replace:

  From:
        private \(.*\) some\(.*\);
  To:
        public \1 getSome\2Value() {
            return some\2;
        }
        public void setSome\2Value(\1 value) {
            some\2 = value;
        } 

  Also note:  The sample keys and mappings were generated manually.
*/


    public static class BeanWithProperties {
        private int someInt;
        private long someLong;
        private double someDouble;
        private float someFloat;
        private short someShort;
        private byte someByte;
        private char someChar;
        private Integer someInteger;
        private String someString;
        private Object someObject;

        public int getSomeIntValue() {
            return someInt;
        }
        public void setSomeIntValue(int value) {
            someInt = value;
        }

        public long getSomeLongValue() {
            return someLong;
        }
        public void setSomeLongValue(long value) {
            someLong = value;
        }

        public double getSomeDoubleValue() {
            return someDouble;
        }
        public void setSomeDoubleValue(double value) {
            someDouble = value;
        }

        public float getSomeFloatValue() {
            return someFloat;
        }
        public void setSomeFloatValue(float value) {
            someFloat = value;
        }

        public short getSomeShortValue() {
            return someShort;
        }
        public void setSomeShortValue(short value) {
            someShort = value;
        }

        public byte getSomeByteValue() {
            return someByte;
        }
        public void setSomeByteValue(byte value) {
            someByte = value;
        }

        public char getSomeCharValue() {
            return someChar;
        }
        public void setSomeCharValue(char value) {
            someChar = value;
        }

        public String getSomeStringValue() {
            return someString;
        }
        public void setSomeStringValue(String value) {
            someString = value;
        }

        public Integer getSomeIntegerValue() {
            return someInteger;
        }
        public void setSomeIntegerValue(Integer value) {
            someInteger = value;
        }

        public Object getSomeObjectValue() {
            return someObject;
        }
        public void setSomeObjectValue(Object value) {
            someObject = value;
        }
    }
    
/*
  note to self.  The Sample keys were generated by copying the field
  declarations and using the following regular expression search and replace:

  From:
        private \(.*\) some\(.*\);
  To:
            "some\2Value", 

  Then, I manually added the "class" key, which is a property that exists for
  all beans (and all objects for that matter.
*/

    public Object[] getSampleKeys() {
        Object[] keys = new Object[] {
            "someIntValue",
            "someLongValue",
            "someDoubleValue",
            "someFloatValue",
            "someShortValue",
            "someByteValue",
            "someCharValue",
            "someIntegerValue",
            "someStringValue",
            "someObjectValue",
            "class",
        };
        return keys;
    }

    /**
     *  An object value that will be stored in the bean map as a value.  Need
     *  to save this externally so that we can make sure the object instances
     *  are equivalent since getSampleValues() would otherwise construct a new
     *  and different Object each time.
     **/
    private Object objectInFullMap = new Object();

/*
    note to self: the sample values were created manually
*/

    public Object[] getSampleValues() {
        Object[] values = new Object[] {
            new Integer(1234),
            new Long(1298341928234L),
            new Double(123423.34),
            new Float(1213332.12f),
            new Short((short)134),
            new Byte((byte)10),
            new Character('a'),
            new Integer(1432),
            "SomeStringValue",
            objectInFullMap,
            BeanWithProperties.class,
        };
        return values;
    }

    public Object[] getNewSampleValues() {
        Object[] values = new Object[] {
            new Integer(223),
            new Long(23341928234L),
            new Double(23423.34),
            new Float(213332.12f),
            new Short((short)234),
            new Byte((byte)20),
            new Character('b'),
            new Integer(232),
            "SomeNewStringValue",
            new Object(),
            null,
        };
        return values;
    }

    /**
     *  The mappings in a BeanMap are fixed on the properties the underlying
     *  bean has.  Adding and removing mappings is not possible, thus this
     *  method is overridden to return false.
     **/
    public boolean isAddRemoveModifiable() {
        return false;
    }
  
    public Map makeFullMap() {
        // note: These values must match (i.e. .equals() must return true)
        // those returned from getSampleValues().
        BeanWithProperties bean = new BeanWithProperties();
        bean.setSomeIntValue(1234);
        bean.setSomeLongValue(1298341928234L);
        bean.setSomeDoubleValue(123423.34);
        bean.setSomeFloatValue(1213332.12f);
        bean.setSomeShortValue((short)134);
        bean.setSomeByteValue((byte)10);
        bean.setSomeCharValue('a');
        bean.setSomeIntegerValue(new Integer(1432));
        bean.setSomeStringValue("SomeStringValue");
        bean.setSomeObjectValue(objectInFullMap);
        return new BeanMap(bean);
    }

    public Map makeEmptyMap() {
        return new BeanMap();
    }

    /**
     *  Need to override this method because the "clear()" method on the bean
     *  map just returns the bean properties to their default states.  It does
     *  not actually remove the mappings as per the map contract.  The default
     *  testClear() methods checks that the clear method throws an
     *  UnsupportedOperationException since this class is not add/remove
     *  modifiable.  In our case though, we do not always throw that exception.
     **/
    public void testClear() {
        //TODO: make sure a call to BeanMap.clear returns the bean to its
        //default initialization values.
    }

    public void testBeanMapClone() {
        BeanMap map = (BeanMap)makeFullMap();
        try {
            BeanMap map2 = (BeanMap)((BeanMap)map).clone();

            // make sure containsKey is working to verify the bean was cloned
            // ok, and the read methods were properly initialized
            Object[] keys = getSampleKeys();
            for(int i = 0; i < keys.length; i++) {
                assertTrue("Cloned BeanMap should contain the same keys",
                           map2.containsKey(keys[i]));
            }
        } catch (CloneNotSupportedException exception) {
            fail("BeanMap.clone() should not throw a " +
                 "CloneNotSupportedException when clone should succeed.");
        }
    }

    public String[] ignoredSimpleTests() {
        // Ignore the serialization tests on collection views.
        return new String[] {
         "TestBeanMap.bulkTestMapEntrySet.testCanonicalEmptyCollectionExists",
         "TestBeanMap.bulkTestMapEntrySet.testCanonicalFullCollectionExists",
         "TestBeanMap.bulkTestMapKeySet.testCanonicalEmptyCollectionExists",
         "TestBeanMap.bulkTestMapKeySet.testCanonicalFullCollectionExists",
         "TestBeanMap.bulkTestMapValues.testCanonicalEmptyCollectionExists",
         "TestBeanMap.bulkTestMapValues.testCanonicalFullCollectionExists",
         "TestBeanMap.bulkTestMapEntrySet.testSimpleSerialization",
         "TestBeanMap.bulkTestMapKeySet.testSimpleSerialization"
        };
    }


    public void testBeanMapPutAllWriteable() {
        BeanMap map1 = (BeanMap)makeFullMap();
        BeanMap map2 = (BeanMap)makeFullMap();
        map2.put("someIntValue", new Integer(0));
        map1.putAllWriteable(map2);
        assertEquals(map1.get("someIntValue"), new Integer(0));
    }

}
