SUMMARY = "Base for Android images"

LICENSE = "MIT"

inherit build_yocto
inherit xt_quirks

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

S = "${WORKDIR}"

################################################################################
# set to AUTOREV so we can override this while reconstructing this build with
# specific commit ID
################################################################################
SRCREV = "${AUTOREV}"

###############################################################################
# extra layers and files to be put after Yocto's do_unpack into inner builder
###############################################################################
# these will be populated into the inner build system on do_unpack_xt_extras
XT_QUIRK_UNPACK_SRC_URI += " \
    file://meta-xt-images-extra \
"

# these layers will be added to bblayers.conf on do_configure
XT_QUIRK_BB_ADD_LAYER += " \
    meta-xt-images-extra \
"

# meta layers needed to build Android native build environment, e.g. openjdk
SRC_URI_append = " \
    git://git.yoctoproject.org/poky;branch=morty;destsuffix=poky;scmdata=keep \
    git://git.yoctoproject.org/meta-java;branch=morty;destsuffix=meta-java;scmdata=keep \
    git://git.openembedded.org/meta-openembedded;branch=morty;destsuffix=meta-openembedded;scmdata=keep \
"

configure_versions_base() {
    local local_conf="${S}/build/conf/local.conf"

    cd ${S}
    # override what xt-distro wants as machine as we will only use
    # machines understood by Poky
    base_update_conf_value ${local_conf} MACHINE "qemux86-64"
}

python do_configure_append() {
    bb.build.exec_func("configure_versions_base", d)
}

