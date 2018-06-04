package gsheets4s
package interpreters

import io.circe.generic.auto._

import http._
import model._

class RestSpreadsheetsValues2[F[_]](client: HttpClient[F]) {
  def get(spreadsheetID: String, range: A1Notation): F[Either[GsheetsError, ValueRange]] =
    client.get(s"$spreadsheetID/$range")

  def update(
    spreadsheetID: String,
    range: A1Notation,
    updates: ValueRange,
    valueInputOption: ValueInputOption
  ): F[Either[GsheetsError, UpdateValuesResponse]] =
    client.put(s"$spreadsheetID/$range", updates,
      List(("valueInputOption", valueInputOption.value)))
}
