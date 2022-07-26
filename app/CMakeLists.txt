cmake_minimum_required(VERSION 3.10)

project(native_code_coverage_test)

message(STATUS "build with coverage")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fprofile-arcs -ftest-coverage")

find_library( # Defines the name of the path variable that stores the
        # location of the NDK library.
        log-lib

        # Specifies the name of the NDK library that
        # CMake needs to locate.
        log )

add_library(native-lib
        SHARED
        ${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/native.cpp
        ${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/a.cpp
        )

target_link_libraries(native-lib PRIVATE ${log-lib})

