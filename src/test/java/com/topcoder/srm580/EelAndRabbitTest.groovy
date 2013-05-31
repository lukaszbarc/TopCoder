package com.topcoder.srm580.div2.second;

import spock.lang.Shared
import spock.lang.Specification

/**
 * @author lukasz.barc
 */
class EelAndRabbitTest extends Specification {

    @Shared EelAndRabbit objectUnderTest = new EelAndRabbit();


    def "should pass topcoder tests"() {
        when:
        int result = objectUnderTest._getmax(par1, par2)

        then:
        result == expected

        where:
        par1                            | par2                                    | expected
        [2, 4, 3, 2, 2, 1, 10]          | [2, 6, 3, 7, 0, 2, 0]                   | 6
        [1, 1, 1]                       | [2, 0, 4]                               | 2
        [8, 2, 1, 10, 8, 6, 3, 1, 2, 5] | [17, 27, 26, 11, 1, 27, 23, 12, 11, 13] | 7
    }
}
