python do_patch_prepend() {
    d.appendVar("SRC_URI", "\n")
    d.appendVar("SRC_URI", d.getVar("XT_SRC_URI") or "")
    bb.debug(1, str(d.getVar("SRC_URI").split()))
}
