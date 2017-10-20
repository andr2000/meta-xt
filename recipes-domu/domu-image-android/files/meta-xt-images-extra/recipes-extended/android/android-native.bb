SUMMARY = "Android"
DESCRIPTION = "Recipe for building Android image"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

S = "${WORKDIR}/repo"

SRCREV = "${AUTOREV}"

# Android uses its own cross-compilers, so fake Yocto and pretend
# we are a native package
inherit android

require inc/xt_shared_env.inc

DEPENDS += "openjdk-8-native"

EXTRA_OEMAKE += "OUT_DIR_COMMON_BASE=${DEPLOY_DIR_IMAGE}"

do_compile_append() {
    export USE_CCACHE=1
    # keep .ccache aside of Yocto's cache
    export CCACHE_DIR=${SSTATE_DIR}/../${PN}/.ccache
    ${S}/prebuilts/misc/linux-x86/ccache/ccache -M 50G
}

