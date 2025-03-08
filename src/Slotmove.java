public class Slotmove {
    private Setobj setobj;
    private ObjTransform objTransform;

    public Slotmove(Setobj setobj, ObjTransform objTransform) {
        this.setobj = setobj;
        this.objTransform = objTransform;
    }

    public void update() {
        setobj.setobj_setobj(objTransform.xPos, objTransform.yPos - 1.2f, 0, 0, setobj);
        setobj.setobj_setobj(objTransform.xPos, objTransform.yPos - 0.9f, 0, 1, setobj);
        setobj.setobj_setobj(objTransform.xPos, objTransform.yPos - 0.6f, 0, 2, setobj);
        setobj.setobj_setobj(objTransform.xPos, objTransform.yPos - 0.3f, 0, 3, setobj);
        setobj.setobj_setobj(objTransform.xPos, objTransform.yPos, 0, 4, setobj);
        setobj.setobj_setobj(objTransform.xPos, objTransform.yPos + 0.3f, 0, 5, setobj);
        setobj.setobj_setobj(objTransform.xPos, objTransform.yPos + 0.6f, 0, 6, setobj);
        setobj.setobj_setobj(objTransform.xPos, objTransform.yPos + 0.9f, 0, 7, setobj);
        setobj.setobj_setobj(objTransform.xPos, objTransform.yPos + 1.2f, 0, 8, setobj);
    }
}
