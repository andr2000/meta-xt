SUMMARY = "A console-only image that fully supports the target device \
hardware."

LICENSE = "MIT"

S = "${WORKDIR}/repo"

SRC_URI = "repo:///home/a2k/projects/build-sandbox/build-manifest.git;manifest=xt-image-weston/rcar/m3ulcb/01-dom0/manifest;protocol=file"

XT_SRC_URI_salvator-x = "file://${S}/meta-renesas/meta-rcar-gen3/docs/sample/patch/patch-for-linaro-gcc/0001-rcar-gen3-add-readme-for-building-with-Linaro-Gcc.patch;patchdir=meta-renesas"

XT_BB_IMAGE_TARGET = "core-image-weston"
XT_BB_LOCAL_CONF_FILE = "meta-demo/meta-rcar-gen3-xen/doc/local-wayland.conf"
XT_BB_LAYERS_FILE = "meta-demo/meta-rcar-gen3-xen/doc/bblayers.conf"

inherit build_yocto
# for R-Car:
inherit patch_quircks
