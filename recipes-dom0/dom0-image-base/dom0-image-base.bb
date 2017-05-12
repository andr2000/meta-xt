SUMMARY = "A console-only image that fully supports the target device \
hardware."

LICENSE = "MIT"

S = "${WORKDIR}/repo"

SRC_URI = "repo://github.com/andr2000/manifests.git;manifest=default;protocol=https;branch=2017"

XT_BB_IMAGE_TARGET = "core-image-weston"
XT_BB_LOCAL_CONF_FILE = "meta-demo/meta-rcar-gen3-xen/doc/local-wayland.conf"
XT_BB_LAYERS_FILE = "meta-demo/meta-rcar-gen3-xen/doc/bblayers.conf"

do_patch() {
    cd ${S}
    # FIXME: this is a hack to patch r-car meta layer
    /usr/bin/patch -p1 <  "${S}/meta-renesas/meta-rcar-gen3/docs/sample/patch/patch-for-linaro-gcc/0001-rcar-gen3-add-readme-for-building-with-Linaro-Gcc.patch"
}

inherit build_yocto
