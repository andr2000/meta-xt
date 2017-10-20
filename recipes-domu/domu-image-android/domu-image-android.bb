SUMMARY = "Base for Android images"

LICENSE = "MIT"

inherit build_yocto
inherit xt_quirks

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
FILESEXTRAPATHS_prepend := "${THISDIR}/../../machine:"

S = "${WORKDIR}"

################################################################################
# set to AUTOREV so we can override this while reconstructing this build with
# specific commit ID
################################################################################
SRCREV = "${AUTOREV}"

XT_BB_LAYERS_FILE = "meta-xt-images-extra/doc/bblayers.conf.domu-image-android"
XT_BB_LOCAL_CONF_FILE = "meta-xt-images-extra/doc/local.conf.domu-image-android"

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
    git://git.yoctoproject.org/poky;branch=pyro;destsuffix=poky;scmdata=keep \
    git://git.yoctoproject.org/meta-java;branch=pyro;destsuffix=meta-java;scmdata=keep \
    git://git.openembedded.org/meta-openembedded;branch=pyro;destsuffix=meta-openembedded;scmdata=keep \
    file://0001-Fix-missing-LICENSE-field-for-u-boot.patch;patchdir=meta-renesas \
    file://0001-Speed-up-repo-synchronization.patch;patchdir=poky \
"

################################################################################
# Renesas R-Car
################################################################################

# FIXME: all gen3 layers are only needed for getting SOC_FAMILY and setting machine overrides
SRC_URI_append_rcar = " \
    git://github.com/renesas-rcar/meta-renesas;destsuffix=meta-renesas;branch=krogoth;scmdata=keep \
"

XT_QUIRK_UNPACK_SRC_URI_append_rcar = "\
    file://meta-xt-images-rcar-gen3 \
"

# these layers will be added to bblayers.conf on do_configure
XT_QUIRK_BB_ADD_LAYER_append_rcar = "\
    meta-renesas/meta-rcar-gen3 \
    meta-xt-images-rcar-gen3 \
"

