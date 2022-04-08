import React from "react";
import { Nav, Button } from "./styled";
const Pagination = ({ total, page, setPage }) => {
  // const numPages = Math.ceil(total);
  const pageNumber = [];
  if (total < 5) {
    for (let i = 1; i < total + 1; i++) {
      pageNumber.push(i);
    }
  } else {
    if (page > 3) {
      for (let i = page - 2; i <= page + 2; i++) {
        if (i > total) break;
        pageNumber.push(i);
      }
    } else {
      for (let i = 1; i <= 5; i++) {
        pageNumber.push(i);
      }
    }
  }

  return (
    <>
      <Nav>
        <Button onClick={() => setPage(page - 1)} disabled={page === 1}>
          &lt;
        </Button>
        {pageNumber.map((i) => (
          <Button
            key={i}
            onClick={() => setPage(i)}
            aria-current={page === i ? "page" : null}
          >
            {i}
          </Button>
        ))}
        <Button onClick={() => setPage(page + 1)} disabled={page === total}>
          &gt;
        </Button>
      </Nav>
    </>
  );
};

export default Pagination;
