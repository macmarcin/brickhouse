package brickhouse.udf.bloom;
/**
 * Copyright 2012 Klout, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/


import java.io.IOException;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.util.bloom.Filter;


@Description(
		 name = "bloom_and",
		 value =  " Returns the logical AND of two bloom filters; representing the intersection of values in both bloom1 AND bloom2  \n " +
		          "_FUNC_(string bloom1, string bloom2) "
		)
public class BloomAndUDF extends UDF {

	public String evaluate( String bloom1Str, String bloom2Str ) throws IOException {
		Filter bloom1 = BloomFactory.GetBloomFilter( bloom1Str);
		Filter bloom2 = BloomFactory.GetBloomFilter( bloom2Str);
		
		bloom1.and( bloom2);
		
		return BloomFactory.WriteBloomToString( bloom1);
	}
}
