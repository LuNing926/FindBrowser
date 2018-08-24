// IMyAidlInterface.aidl
package openthos.com.findbrowser;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<ResolveInfo> getInfo(int tag);
}
