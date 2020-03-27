package fr.polytech.jydet.utils;

public class FormBuilder {

    private StringBuilder sb;

    public FormBuilder(Method method) {
        this("", method);
    }

    public FormBuilder(String action, Method method) {
        sb = new StringBuilder("<form action='")
                .append(action)
                .append("' method='").append(method.name().toLowerCase()).append("'>");
    }

    public FormBuilder addSubmit(String value) {
        sb.append("<div><input type=\"submit\" value=\"").append(value).append("\"></div>");
        return this;
    }

    public String build() {
        return sb.append("</form>").toString();
    }

    public FormBuilder addField(FieldType type, String id, String label, String initialValue) {
        sb.append("<div><label for='").append(id).append("'>").append(label).append("</label><input type='").append(type.name().toLowerCase())
                .append("' name='").append(id)
                .append("' id='").append(id).append("' value='").append(initialValue)
                .append("'></div>");
        return this;
    }

    public RadioBuilder addRadio(String name) {
        return new RadioBuilder(this, name);
    }

    private void addText(String txt) {
        sb.append(txt);
    }

    public static class RadioBuilder {
        private StringBuilder sb;
        private FormBuilder formBuilder;
        private String name;

        public RadioBuilder(FormBuilder formBuilder, String name) {
            this.formBuilder = formBuilder;
            this.name = name;
            sb = new StringBuilder();
        }

        public RadioBuilder addRadioButton(String id, boolean inline) {
            return addRadioButton(id, id, inline);
        }

        public RadioBuilder addRadioButton(String id, String label, boolean inline) {
            if (! inline) {
                sb.append("<div>");
            }
            sb.append("<input type='radio' name='").append(name).append("' id='").append(id)
                    .append("' value='").append(id).append("'><label for='").append(id).append("'>")
                    .append(label).append("</label>");
            if (! inline) {
                sb.append("</div>");
            }
            return this;
        }

        public FormBuilder build() {
            formBuilder.addText(sb.toString());
            return formBuilder;
        }
    }

    public static enum Method {
        PUT, GET, POST, DELETE
    }

    public static enum FieldType {
        TEXT, PASSWORD, NUMBER
    }
}
