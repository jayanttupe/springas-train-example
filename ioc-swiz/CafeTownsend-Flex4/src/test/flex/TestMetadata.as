/**
 * Created by IntelliJ IDEA.
 * User: hujl
 * Date: 11-11-24
 * Time: 下午2:16
 * To change this template use File | Settings | File Templates.
 */
package {
import flexunit.framework.Assert;

import org.osflash.thunderbolt.Logger;

public class TestMetadata {
    public function TestMetadata() {
    }

    [Before (order=1)]
    public function runBeforeEveryTest():void {
//        simpleMath = new SimpleMath();
//        trace("before 1...");
        Logger.error("before 1...")
    }

    [Before (order=2)]
    public function alsoRunBeforeEveryTest():void {
//        simpleMath1 = new SimpleMath();
        trace("before 2...");
    }

    [After]
    public function runAfterEveryTest():void {
//        simpleMath = null;
//        simpleMath1 = null;
        trace("after ...");
    }

    [Test]
    public function addition():void {
//        Assert.assertEquals(12, simpleMath.add(7, 5));
        Assert.assertEquals(12, 7+5);
    }

    [Test]
    public function subtraction():void {
//        Assert.assertEquals(9, simpleMath.subtract(12, 3));
        Assert.assertEquals(9, 12-3);
    }


}
}
