package core.verify;

import annotation.ToSingleton;

/**
 * Created by vicboma on 27/09/14.
 */
@ToSingleton(ToClass = "core.verify.Verify")
public interface IVerify {
    String toString();
}
