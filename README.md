# Android APK gcov code coverage example
1. compile c/c++ with `-fprofile-arcs -ftest-coverage`
2. build apk and shared library, then you can find *.gcno files in local build directory
3. set `GCOV_PREFIX` and `GCOV_PREFIX_STRIP` to specify *.gcda save directory(in your phone), then run your app
4. pull *.gcda files to local directory
5. use gcov/lcov/gcovr to generate reports