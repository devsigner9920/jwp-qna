package qna.domain.commons;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

@Embeddable
public class Contents {
  @Lob
  private String contents;

  protected Contents() {}

  public Contents(String contents) {
    this.contents = contents;
  }

  public static Contents of(String contents) {
    return new Contents(contents);
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Contents contents1 = (Contents) o;
    return Objects.equals(contents, contents1.contents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contents);
  }
}
